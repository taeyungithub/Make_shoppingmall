package com.nhnacademy.shoppingmall.controller.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutController {
    //todo#13-3 로그아웃 구현
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();

        return "redirect:/";
    }
}
