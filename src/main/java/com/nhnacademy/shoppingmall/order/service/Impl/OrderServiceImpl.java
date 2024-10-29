package com.nhnacademy.shoppingmall.order.service.Impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        return  orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void placeOrder(Order order) {
        if (getOrder(order.getOrderId()) != null) {
            throw new IllegalStateException("이미 존재하는 주문입니다.");
        }
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(int orderId) {
        if (getOrder(orderId) == null) {
            throw new IllegalStateException("주문을 찾을 수 없습니다.");
        }
        orderRepository.deleteByOrderId(orderId);
    }

}
