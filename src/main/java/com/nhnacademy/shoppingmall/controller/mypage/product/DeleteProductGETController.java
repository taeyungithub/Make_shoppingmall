package com.nhnacademy.shoppingmall.controller.mypage.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/mypage/deleteProduct.do")
public class DeleteProductGETController implements BaseController {
        @Override
        public String execute(HttpServletRequest req, HttpServletResponse resp) {
            return "shop/mypage/deleteproductForm";
        }
}


