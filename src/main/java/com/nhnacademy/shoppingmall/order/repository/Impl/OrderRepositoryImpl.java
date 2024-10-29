package com.nhnacademy.shoppingmall.order.repository.Impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Optional<Order> findById(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getString("user_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    );
                    order.setOrderId(rs.getInt("order_id"));
                    order.setTotalPrice(rs.getLong("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                    return Optional.of(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            List<Order> orders = new ArrayList<>();

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getString("user_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    );
                    order.setOrderId(rs.getInt("order_id"));
                    order.setTotalPrice(rs.getLong("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());

                    orders.add(order);
                }
            }
            return orders;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Order> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM orders";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = psmt.executeQuery()) {
                List<Order> orders = new ArrayList<>();
                while (rs.next()) {
                    Order order = new Order(
                            rs.getString("user_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity")
                    );
                    order.setOrderId(rs.getInt("order_id"));
                    order.setTotalPrice(rs.getLong("total_price"));
                    order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                    orders.add(order);
                }
                return orders;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public int save(Order order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO orders (user_id, product_id, quantity, total_price, order_date) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            psmt.setString(1, order.getOrdereduserId());
            psmt.setInt(2, order.getOrderedproductId());
            psmt.setInt(3, order.getQuantity());
            psmt.setLong(4, order.getTotalPrice());
            psmt.setTimestamp(5, Timestamp.valueOf(order.getOrderDate()));

            int affectedRows = psmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderId(generatedKeys.getInt(1));
                    }
                }
            }
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByOrderId(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, orderId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}