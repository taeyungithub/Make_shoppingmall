package com.nhnacademy.shoppingmall.controller.shopping;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/orderAction.do")
public class OrderController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final OrderRepository orderRepository = new OrderRepositoryImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        log.info("user = {}", user);

        // 세션에서 장바구니 데이터 가져오기
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");
        log.info("quantityMap = {}", quantityMap);

        if (quantityMap == null || quantityMap.isEmpty()) {
            log.error("주문 실패: 장바구니가 비어 있습니다.");
            return "redirect:/shopping/cart.do";
        }

        // 주문 처리
        for (Map.Entry<Integer, Integer> entry : quantityMap.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            Product product = productService.getProduct(productId);
            if (product == null || product.getStock() < quantity) {
                log.error("주문 실패: 재고가 부족한 상품이 있습니다. 상품 ID: {}", productId);
                continue;
            }

            long totalPrice = product.getProductPrice() * quantity;
            if (user.getUserPoint() < totalPrice) {
                log.error("주문 실패: 포인트가 부족하여 주문할 수 없습니다.");
                continue;
            }

            // 주문 생성 및 저장
            Order order = new Order(user.getUserId(), productId, quantity);
            log.info("order = {}", order);
            orderRepository.save(order);

            // 재고 및 사용자 포인트 업데이트
            product.setStock(product.getStock() - quantity);
            productService.updateProduct(product);
            user.setUserPoint(user.getUserPoint() - (int) totalPrice);
            userService.updateUser(user);

            // 포인트 적립 비동기 처리
            new Thread(() -> {
                DbConnectionThreadLocal.initialize(); // 스레드 로컬 트랜잭션 초기화
                try {
                    int earnedPoints = (int) (totalPrice * 0.1);
                    user.setUserPoint(user.getUserPoint() + earnedPoints);
                    userService.updateUser(user);
                    log.info("포인트 적립 완료: {} 포인트 적립", earnedPoints);
                    DbConnectionThreadLocal.getConnection().commit(); // 커밋
                } catch (Exception e) {
                    try {
                        DbConnectionThreadLocal.getConnection().rollback(); // 오류 시 롤백
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    log.error("포인트 적립 실패: {}", e.getMessage(), e);
                } finally {
                    DbConnectionThreadLocal.reset(); // 트랜잭션 정리
                }
            }).start();
        }

        // 주문 완료 후 장바구니 초기화
        session.removeAttribute("quantityMap");

        return "redirect:/";
    }
}
