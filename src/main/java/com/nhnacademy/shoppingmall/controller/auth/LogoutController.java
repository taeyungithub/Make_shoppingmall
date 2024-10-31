package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/logout.do")
public class LogoutController implements BaseController {
    //todo#13-3 로그아웃 구현
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        log.info("로그아웃");
        return "redirect:/";
    }
}
