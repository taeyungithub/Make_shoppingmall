package com.nhnacademy.shoppingmall.controller.mypage.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/userList.do")
public class UserListController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = userService.getUsers();

        List<User> admins = users.stream()
                .filter(user -> user.getUserAuth() == User.Auth.ROLE_ADMIN)
                .collect(Collectors.toList());

        List<User> generalUsers = users.stream()
                .filter(user -> user.getUserAuth() == User.Auth.ROLE_USER)
                .collect(Collectors.toList());

        req.setAttribute("admins", admins);
        req.setAttribute("generalUsers", generalUsers);

        return "shop/mypage/userList";
    }
}
