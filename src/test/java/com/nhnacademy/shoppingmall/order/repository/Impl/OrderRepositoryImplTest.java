package com.nhnacademy.shoppingmall.order.repository.Impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.Impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderRepositoryImplTest {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private final OrderRepository orderRepository = new OrderRepositoryImpl();
    private final AddressRepository addressRepository = new AddressRepositoryImpl();

    private Order testOrder;
    private User testUser;
    private Product testProduct;
    private Address address;


    @BeforeEach
    void setUp() throws SQLException {
        DbConnectionThreadLocal.initialize();

        testUser = new User("nhnacademy-test-user", "nhn아카데미", "nhnacademy-test-password", "19900505", User.Auth.ROLE_USER, 100_0000, LocalDateTime.now(), LocalDateTime.now());
        testProduct = new Product(1, "Test Product", "", 10000L, "Test Description", 10);
        address = new Address(0, "asdf", testUser.getUserId());

        userRepository.save(testUser);
        productRepository.save(testProduct);
        addressRepository.save(address);

        log.info(testUser.getUserId());
        log.info(String.valueOf(testProduct.getProductId()));
        log.info(String.valueOf(address.getAddressId()));

        testOrder = new Order(testUser.getUserId(), testProduct.getProductId(), 2, address.getAddressId());
        testOrder.setTotalPrice(20000L);
        testOrder.setOrderDate(LocalDateTime.now());
        orderRepository.save(testOrder);
    }

    @AfterEach
    void tearDown() throws SQLException {
        orderRepository.deleteByOrderId(testOrder.getOrderId());
        userRepository.deleteByUserId(testUser.getUserId());
        productRepository.deleteByProductId(testProduct.getProductId());
        addressRepository.deleteById(address.getAddressId());
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("Find Order by ID")
    void findById() {
        Optional<Order> foundOrder = orderRepository.findById(testOrder.getOrderId());
        assertTrue(foundOrder.isPresent());
        assertEquals(testOrder.getOrdereduserId(), foundOrder.get().getOrdereduserId());
    }

    @Test
    @DisplayName("Find Orders by User ID")
    void findByUserId() {
        List<Order> orders = orderRepository.findByUserId(testOrder.getOrdereduserId());
        assertEquals(testOrder.getOrdereduserId(), orders.get(0).getOrdereduserId());
    }

    @Test
    @DisplayName("Save Order")
    void save() {
        Order newOrder = new Order(testUser.getUserId(), 102, 1, 2);
        newOrder.setTotalPrice(15000L);
        newOrder.setOrderDate(LocalDateTime.now());

        int result = orderRepository.save(newOrder);
        assertEquals(1, result);
        assertTrue(newOrder.getOrderId() > 0);

        orderRepository.deleteByOrderId(newOrder.getOrderId());
    }

    @Test
    @DisplayName("Delete Order by ID")
    void deleteByOrderId() {
        int result = orderRepository.deleteByOrderId(testOrder.getOrderId());
        assertEquals(1, result);
        assertFalse(orderRepository.findById(testOrder.getOrderId()).isPresent());
    }
}
