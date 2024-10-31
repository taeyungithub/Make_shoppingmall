package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value ="/signupAction.do")
public class SignupPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String userName = req.getParameter("user_name");
        String userPassword = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");
        User.Auth userAuth = User.Auth.valueOf(req.getParameter("user_auth"));
        int point = 1000000;
        LocalDateTime timestamp = LocalDateTime.now();
        LocalDateTime lastestLoginAt = null;

        User user = new User(userId, userName, userPassword, userBirth, userAuth, point, timestamp, lastestLoginAt);

        log.info( user.getUserName() + " -> 회원가입중");

        try {
            userService.saveUser(user);
        } catch (UserAlreadyExistsException e) {
            req.setAttribute("errorMessage", "이미 존재하는 아이디입니다.");
            return "shop/error";
        }

        log.info("회원가입 성공");

        return  "shop/main/index";
    }
}
