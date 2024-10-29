package com.nhnacademy.shoppingmall.controller.mypage.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST,value = "/mypage/deleteUser.do")
public class DeleteUserController implements BaseController {

    UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");

        try{
            userService.getUser(userId);
        }catch (RuntimeException e){
            log.info("삭제할 회원이 없습니다");
        }
        userService.deleteUser(userId);
        log.info("회원삭제 완료했습니다.");

        HttpSession session = req.getSession();
        session.removeAttribute("user");

        return "redirect:/";
    }
}
