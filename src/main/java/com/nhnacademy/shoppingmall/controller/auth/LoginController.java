package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/login.do")
public class LoginController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-1 session이 존재하고 로그인이 되어 있다면 redirect:/index.do 반환 합니다.
        if (req.getSession().getAttribute("user") != null ) {
            log.debug("이미 로그인되있음");
            return "redirect:/index.do";
        }
        log.debug("로그인 안되있음");
        return "shop/login/login_form";
    }
}
