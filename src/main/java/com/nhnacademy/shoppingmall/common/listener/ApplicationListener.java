package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.time.LocalDateTime;

@Slf4j
@WebListener
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DbConnectionThreadLocal.initialize();

        try {
            if (userService.getUser("admin") == null) {
                userService.saveUser(new User("admin", "admin", "12345", "19990101", User.Auth.ROLE_ADMIN, 1000000, LocalDateTime.now(), null));
            } else {
                log.info("Admin account already exists.");
            }

            if (userService.getUser("user") == null) {
                userService.saveUser(new User("user", "user", "12345", "19990101", User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), null));
            } else {
                log.info("User account already exists.");
            }
        } catch (Exception e) {
            throw e;
        }
        DbConnectionThreadLocal.reset();
    }

}
