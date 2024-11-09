package com.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor   // 기본 생성자 자동 생성
@AllArgsConstructor  // 모든 필드를 초기화하는 생성자 자동 생성
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 자동 생성
    private int addressId;

    @Column(nullable = false, length = 200)  // NOT NULL 제약 조건, 최대 길이 100 설정
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // 외래 키로 User 엔티티와 연결
    private User user;
}
