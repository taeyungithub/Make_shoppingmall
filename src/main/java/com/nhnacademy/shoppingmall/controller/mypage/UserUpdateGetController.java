package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET,value = "/mypage/update.do")
public class UserUpdateGetController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        log.info("내정보 수정 이동");
        return "shop/mypage/update_form";
    }
}