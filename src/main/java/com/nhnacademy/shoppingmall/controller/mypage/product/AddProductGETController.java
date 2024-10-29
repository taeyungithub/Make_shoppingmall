package com.nhnacademy.shoppingmall.controller.mypage.product;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET,value = "/mypage/addProduct.do")
public class AddProductGETController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "shop/mypage/addproductForm";
    }
}
