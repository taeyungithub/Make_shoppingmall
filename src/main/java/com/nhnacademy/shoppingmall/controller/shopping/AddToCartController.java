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
        if (product == null) {
            log.error("상품을 찾을 수 없습니다. ID: {}", productId);
            return "redirect:/";
        }

        HttpSession session = req.getSession();

        // 장바구니 리스트 가져오기 또는 초기화
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedList<>();
            session.setAttribute("cart", cart);
        }

        // 수량 맵 가져오기 또는 초기화
        Map<Integer, Integer> quantityMap = (Map<Integer, Integer>) session.getAttribute("quantityMap");
        if (quantityMap == null) {
            quantityMap = new HashMap<>();
            session.setAttribute("quantityMap", quantityMap);
        }

        // 세션에 저장된 stockMap 가져오기 또는 초기화
        Map<Integer, Integer> stockMap = (Map<Integer, Integer>) session.getAttribute("stockMap");
        if (stockMap == null) {
            stockMap = new HashMap<>();
            session.setAttribute("stockMap", stockMap);
        }

        // 현재 장바구니에 있는 수량을 확인하고 총 수량 확인
        int currentQuantity = quantityMap.getOrDefault(productId, 0);
        int availableStock = stockMap.getOrDefault(productId, product.getStock());

        // 재고가 허용하는 최대 수량까지만 요청 수량을 증가시키기
        int totalRequestedQuantity = Math.min(currentQuantity + requestStock, availableStock);

        // 장바구니에 이미 있는 상품인지 확인 후 추가
        if (!cart.contains(product)) {
            cart.add(product); // 장바구니에 상품 추가 (처음 추가하는 경우)
        }

        // 수량 맵 및 세션 내의 재고 맵 업데이트
        quantityMap.put(productId, totalRequestedQuantity);
        stockMap.put(productId, availableStock); // 재고 업데이트는 주문 시에만 적용

        log.info("장바구니에 추가 완료: 제품 ID={}, 요청 수량={}, 현재 재고={}", productId, totalRequestedQuantity, availableStock);


        return "redirect:/shopping/cart.do";
    }
}
