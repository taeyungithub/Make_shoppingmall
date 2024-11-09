package com.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public enum Auth {
        ROLE_ADMIN, ROLE_USER
    }

    @Id
    @Column(length = 50, nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 200)
    private String userPassword;

    @Column(nullable = false, length = 10)
    private String userBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Auth userAuth;

    @Column(nullable = false)
    private int userPoint;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime latestLoginAt;

    @OneToMany(mappedBy = "user")  // User가 여러 Order를 가질 수 있도록 설정
    private List<Order> orders;

    @OneToMany(mappedBy = "user")  // User가 여러 Address를 가질 수 있도록 설정
    private List<Address> addresses;
}
