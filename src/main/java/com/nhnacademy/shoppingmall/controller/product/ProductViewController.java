package com.nhnacademy.shoppingmall.controller.product;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/product/productView.do")
public class ProductViewController implements BaseController{
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String input = req.getParameter("productID");
        log.info("input : {}", input);
        int productID = Integer.parseInt(input);
        log.info("Product ID: {}", productID);
        Product product = productService.getProduct(productID);
        log.info("Product: {}", product);
        req.setAttribute("product", product);

        return "shop/product/productView";
    }
}

//String input = req.getParameter("productID");
//int productID = Integer.parseInt(req.getParameter("productID"));
//Product product = productService.getProduct(productID);
//        log.debug("img 값 " + product.getProductImage());
//        req.setAttribute("product", product);
