package com.nhnacademy.shoppingmall.order.domain;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Order {
    private int orderId;            // 주문 ID
    private String OrdereduserId;              // 주문한 사용자 정보
    private int OrderedproductId;        // 주문한 상품
    private int quantity;           // 주문 수량
    private long totalPrice;        // 총 주문 가격
    private LocalDateTime orderDate;// 주문 날짜

    // 주문 생성자
    public Order(String userId, int productId, int quantity) {
        this.OrdereduserId = userId;
        this.OrderedproductId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice(productId);
        this.orderDate = LocalDateTime.now();
    }

    public long totalPrice(int productId) {
        ProductService ps = new ProductServiceImpl(new ProductRepositoryImpl());
        Product product = ps.getProduct(productId);
        long price = product.getProductPrice();


        return price * quantity;
    }

    public int getOrderedproductId() {
        return OrderedproductId;
    }

    public String getOrdereduserId() {
        return OrdereduserId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrdereduserId(String ordereduserId) {
        OrdereduserId = ordereduserId;
    }

    public void setOrderedproductId(int orderedproductId) {
        OrderedproductId = orderedproductId;
    }
}
