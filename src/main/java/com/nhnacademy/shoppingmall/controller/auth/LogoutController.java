package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST,value = "/logout.do")
public class LogoutController {
    //todo#13-3 로그아웃 구현
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();

        return "redirect:/";
    }
}
