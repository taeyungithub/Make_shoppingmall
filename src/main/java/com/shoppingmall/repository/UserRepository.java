package com.shoppingmall.repository;

import com.shoppingmall.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // 아이디와 비밀번호로 사용자 조회 (로그인용)
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);

    // 마지막 로그인 시간 업데이트
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.latestLoginAt = :latestLoginAt WHERE u.userId = :userId")
    void updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.userName = :userName, u.userPassword = :userPassword, u.userBirth = :userBirth, u.userAuth = :userAuth, u.userPoint = :userPoint WHERE u.userId = :userId")
    int updateUserDetails(String userId, String userName, String userPassword, String userBirth, User.Auth userAuth, int userPoint);

}
