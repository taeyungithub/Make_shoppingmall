package com.nhnacademy.shoppingmall.user.domain;

import java.time.LocalDateTime;
import java.util.Objects;

// 사용자
public class User {
    // 권한 enum
    public enum Auth {
        ROLE_ADMIN, ROLE_USER
    }

    private String userId;                      // 사용자 아이디 (pk)
    private String userName;                    // 사용자 이름
    private String userPassword;                // 사용자 비밀번호
    private String userBirth;                   // 사용자 생년월일
    private Auth userAuth;                      // 사용자 권한
    private int userPoint;                      // 사용자 포인트
    private LocalDateTime createdAt;            // 계정 만든 날
    private LocalDateTime latestLoginAt;        // 마지막 로그인

    // 사용자 생성자
    public User(String userId, String userName, String userPassword, String userBirth, Auth userAuth, int userPoint, LocalDateTime createdAt, LocalDateTime latestLoginAt) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userAuth = userAuth;
        this.userPoint = userPoint;
        this.createdAt = createdAt;
        this.latestLoginAt = latestLoginAt;
    }

    // get
    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public Auth getUserAuth() {
        return userAuth;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLatestLoginAt() {
        return latestLoginAt;
    }

    // set
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public void setUserAuth(Auth userAuth) {
        this.userAuth = userAuth;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

}