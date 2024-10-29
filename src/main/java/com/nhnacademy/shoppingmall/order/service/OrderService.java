package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;

import java.util.List;

public interface OrderService {
    Order getOrder(int orderId);
    List<Order> getOrderByUserId(String userId);
    List<Order> getAllOrders();
    void placeOrder(Order order);
    void cancelOrder(int orderId);
}
