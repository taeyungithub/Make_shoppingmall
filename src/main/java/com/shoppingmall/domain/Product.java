package com.shoppingmall.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)  // 외래 키로 Category 엔티티와 연결
    private Category category;

    @Column(nullable = false, length = 200)
    private String productName;

    @Column(nullable = false, length = 200)
    private String productImage;

    @Column(nullable = false)
    private long productPrice;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private int stock;
}
