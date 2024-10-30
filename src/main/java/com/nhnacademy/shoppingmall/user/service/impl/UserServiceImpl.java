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
        // 사용자 조회 및 검증
        User user = userRepository.findByUserIdAndUserPassword(userId, userPassword).orElse(null);
        if (user == null) {
            log.debug("로그인 실패");
            throw new UserNotFoundException(userId);
        }

        // 현재 날짜와 마지막 로그인 날짜 가져오기
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastLogin = user.getLatestLoginAt();

        // 마지막 로그인과 오늘 날짜의 일자를 비교하여 포인트 지급 여부 결정
        if (lastLogin == null || lastLogin.toLocalDate().isBefore(now.toLocalDate())) {
            int dailyBonusPoints = 10000;  // 지급할 포인트
            user.setUserPoint(user.getUserPoint() + 10000);  // 포인트 추가
            log.debug("일일 첫 로그인 포인트 {}점이 지급되었습니다.", dailyBonusPoints);
        }

        // 로그인 시간 업데이트
        userRepository.updateLatestLoginAtByUserId(userId, now);
        userRepository.update(user);  // 포인트가 변경된 사용자 정보 업데이트
        log.debug("로그인 성공");

        return user;
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
