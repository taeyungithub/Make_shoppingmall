package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(int orderId); // 주문 ID로 조회
    List<Order> findByUserId(String userId); // 주문 ID로 조회
    List<Order> findAll();                 // 모든 주문 조회
    int save(Order order);                 // 주문 저장
    int deleteByOrderId(int orderId);      // 주문 ID로 삭제
}
