package com.nhnacademy.shoppingmall.controller.mypage.product;

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
@RequestMapping(method = RequestMapping.Method.POST,value = "/mypage/addProductAction.do")
public class AddProductPostController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String categoryID = req.getParameter("categoryID"); // 카테고리 ID
        String productName = req.getParameter("productName"); // 상품 이름
        String productImage = req.getParameter("productImage"); // 이미지 URL
        String productPrice = req.getParameter("productPrice"); // 가격
        String description = req.getParameter("description"); // 상품 설명
        int stock = Integer.parseInt(req.getParameter("stock"));

        req.setAttribute("successAdd", false);
        req.setAttribute("errorAdd", false);

        if (categoryID.isEmpty() || productName.isEmpty() || productPrice.isEmpty() || description.isEmpty()) {
            req.setAttribute("errorAdd", true);
            log.info("잘못입력하여 추가 실패");
            return "shop/mypage/addproductForm";
        }

        if (productImage.isEmpty()) {
            productImage = "/resources/no-image.png";
        }

        log.info("product 객체 생성");
        Product product = new Product();
        product.setCategoryId(Integer.parseInt(categoryID));
        product.setProductName(productName);
        product.setProductImage(productImage);
        product.setProductPrice(Long.parseLong(productPrice));
        product.setDescription(description);
        product.setStock(stock);
        log.info("product 객체 생성 완료");

        productService.saveProduct(product);
        log.info("product 객체 저장 완료");

        req.setAttribute("successAdd", true);
        return "shop/mypage/admin_page";
    }
}
