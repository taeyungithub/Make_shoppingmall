package com.nhnacademy.shoppingmall.user.service.impl;

import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userId) {
        //todo#4-1 회원조회
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void saveUser(User user) {
        //todo#4-2 회원등록
        if (userRepository.countByUserId(user.getUserId()) > 0) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        //todo#4-3 회원수정
        if (userRepository.countByUserId(user.getUserId()) == 0) {
            throw new UserNotFoundException(user.getUserId());
        }
        userRepository.update(user);
    }

    @Override
    public void deleteUser(String userId) {
        //todo#4-4 회원삭제
        if (userRepository.countByUserId(userId) == 0) {
            throw new UserNotFoundException(userId);
        }
        userRepository.deleteByUserId(userId);
        log.debug("삭제완료");
    }

    @Override
    public User doLogin(String userId, String userPassword) {
        User user = null;
        try {
            user = userRepository.findByUserIdAndUserPassword(userId, userPassword)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            log.info("로그인 성공");
        } catch (UserNotFoundException e) {
            log.error("로그인 실패");
            throw e;
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastLogin = user.getLatestLoginAt();

        if (lastLogin == null || lastLogin.toLocalDate().isBefore(now.toLocalDate())) {
            int dailyPoints = 10000;
            user.setUserPoint(user.getUserPoint() + 10000);
            log.debug("일일 첫 로그인 포인트 지급", dailyPoints);
        }

        userRepository.updateLatestLoginAtByUserId(userId, now);
        userRepository.update(user);
        log.debug("로그인 성공");

        return user;
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
