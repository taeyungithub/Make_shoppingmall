package com.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // 외래 키로 User 엔티티와 연결
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // 외래 키로 Product 엔티티와 연결
    private Product product;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)  // 외래 키로 Address 엔티티와 연결
    private Address address;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private long totalPrice;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime orderDate;
}
