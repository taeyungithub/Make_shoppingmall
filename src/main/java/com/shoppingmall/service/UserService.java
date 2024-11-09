package com.shoppingmall.service;

import com.shoppingmall.domain.User;
import com.shoppingmall.repository.UserRepository;
import com.shoppingmall.service.exception.UserAlreadyExistsException;
import com.shoppingmall.service.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public void saveUser(User user) {
        if (userRepository.existsById(user.getUserId())) {
            throw new UserAlreadyExistsException(user.getUserId());
        }
        userRepository.save(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Transactional
    public void updateUser(User user) {
        // 조회 없이 바로 업데이트
        int updatedCount = userRepository.updateUserDetails(
                user.getUserId(),
                user.getUserName(),
                user.getUserPassword(),
                user.getUserBirth(),
                user.getUserAuth(),
                user.getUserPoint()
        );

        if (updatedCount == 0) {
            throw new UserNotFoundException(user.getUserId());
        }
        log.debug("사용자 {} 정보 업데이트 완료", user.getUserId());
    }

    public void deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        userRepository.deleteById(userId);
        log.debug("사용자 {} 삭제 완료", userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User doLogin(String userId, String userPassword) {
        User user = null;
        try {
            user = userRepository.findByUserIdAndUserPassword(userId, userPassword).orElseThrow(() -> new UserNotFoundException(userId));
        } catch (UserNotFoundException e) {
            log.debug("로그인 실패");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastLogin = user.getLatestLoginAt();

        //일일 첫 로그인 포인트 지급 로직
        if (lastLogin == null || lastLogin.toLocalDate().isBefore(now.toLocalDate())) {
            user.setUserPoint(user.getUserPoint() + 10000);
            log.debug("일일 첫 로그인 포인트 지급: {} 포인트", 10000);
        }

        userRepository.updateLatestLoginAtByUserId(userId, now);
        log.debug("로그인 성공 = 사용자 {}", userId);

        return user;
    }

}
