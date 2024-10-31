package com.nhnacademy.shoppingmall.controller.mypage.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/admin/mypage/deleteProductAction.do")
public class DeleteProductPostController implements BaseController {
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int prodcutID = Integer.parseInt(req.getParameter("productID"));

        try {
            productService.deleteProduct(prodcutID);
        } catch (RuntimeException e) {
            log.info("삭제할 id가 없음");
            req.setAttribute("errorMessage", "삭제할 상품이 없습니다.");
            return "shop/error";
        }
        log.info("삭제완료");
        return "shop/mypage/admin_page";
    }
}
