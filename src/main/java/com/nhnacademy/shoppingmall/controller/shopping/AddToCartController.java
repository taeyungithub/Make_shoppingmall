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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/shopping/addToCart.do")
public class AddToCartController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("productID");
        Product product = productService.getProduct(Integer.parseInt(productId));
        req.setAttribute("product", product);

        HttpSession session = req.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart == null) {
            cart = new LinkedList<>();
            session.setAttribute("cart", cart);
        }

        Iterator<Product> iterator = cart.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductId() == product.getProductId()) {
                return "shop/shopping/cart";
            }
        }

        cart.add(product);
        log.info("장바구니에 추가완료");

        return "shop/shopping/cart";
    }
}