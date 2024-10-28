package com.nhnacademy.shoppingmall.controller.product;

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
@RequestMapping(method = RequestMapping.Method.POST, value = "/product/productView.do")
public class ProductViewController implements BaseController {
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("productID");
        Product product = productService.getProduct(Integer.parseInt(productId));
        req.setAttribute("product", product);

        HttpSession session = req.getSession();
        List<Product> recentProducts = (List<Product>) session.getAttribute("recentProducts");

        if (recentProducts == null) {
            recentProducts = new LinkedList<>();
            session.setAttribute("recentProducts", recentProducts);
        }

        Iterator<Product> iterator = recentProducts.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductId() == product.getProductId()) {
                iterator.remove();
            }
        }

        recentProducts.add(product);
        log.debug("최근본상품: 추가완료");

        if (recentProducts.size() > 5) {
            recentProducts.remove(0);
        }

        return "shop/product/productView";
    }
}
