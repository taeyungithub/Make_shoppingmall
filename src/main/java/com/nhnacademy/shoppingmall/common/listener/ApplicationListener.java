package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.LocalDateTime;

@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        log.info("Application start. accounts save");

        if (userService.getUser("admin") == null) {
            userService.saveUser(new User("admin", "admin", "12345", "1970-01-01", User.Auth.ROLE_ADMIN, 1000000, LocalDateTime.now(), null));
            log.info("Admin account created.");
        } else {
            throw new UserAlreadyExistsException("Admin account already exists.");
        }

        if (userService.getUser("user") == null) {
            userService.saveUser(new User("user", "user", "12345", "1980-01-01", User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), null));
            log.info("User account created.");
        } else {
            throw new UserAlreadyExistsException("Admin account already exists.");
        }
    }
}
