package com.nhnacademy.shoppingmall.controller.shopping;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/shopping/addToCart.do")
public class AddToCartController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int productId = Integer.parseInt(req.getParameter("productID"));
        int requestStock = Integer.parseInt(req.getParameter("request_stock")); // 주문 수량

        Product product = productService.getProduct(productId);
        if (product.getStock() < requestStock) {
            log.error("장바구니 추가 실패: 요청한 수량이 재고보다 많습니다.");
            return "redirect:/";
        }

        HttpSession session = req.getSession();

        // 장바구니 리스트 가져오기
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedList<>();
            session.setAttribute("cart", cart);
        }

        // 수량 맵 가져오기 또는 생성
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");
        if (quantityMap == null) {
            quantityMap = new HashMap<>();
            session.setAttribute("quantityMap", quantityMap);
        }

        // 상품 수량 및 장바구니 추가
        product.setStock(product.getStock() - requestStock); // 재고 감소
        productService.updateProduct(product);
        quantityMap.put(productId, requestStock); // 수량 저장
        cart.add(product);

        log.info("장바구니에 추가 완료: 제품 ID={}, 수량={}", productId, requestStock);

        return "redirect:/shopping/cart.do";
    }
}