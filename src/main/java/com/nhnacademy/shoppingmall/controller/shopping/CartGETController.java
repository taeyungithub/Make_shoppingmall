package com.nhnacademy.shoppingmall.controller.shopping;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/shopping/cart.do")
public class CartGETController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("장바구니 이동");
        return "shop/shopping/cart";
    }
}
