package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
@Slf4j
public class PointChannelRequest extends ChannelRequest {
    private final User user;
    private final int pointsToAdd;
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    public PointChannelRequest(User user, int pointsToAdd) {
        this.user = user;
        this.pointsToAdd = pointsToAdd;
    }

    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        try {
            user.setUserPoint(user.getUserPoint() + pointsToAdd);
            userService.updateUser(user); // 업데이트 호출 추가
            log.info("포인트 적립 완료: {} 포인트 적립", pointsToAdd);
            DbConnectionThreadLocal.getConnection().commit();
        } catch (Exception e) {
            try {
                DbConnectionThreadLocal.getConnection().rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            log.error("포인트 적립 실패: {}", e.getMessage());
        } finally {
            DbConnectionThreadLocal.reset();
        }
    }
}
