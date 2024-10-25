package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findById(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("product_name"),
                            resultSet.getInt("category_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("product_image"),
                            resultSet.getLong("product_price"),
                            resultSet.getString("product_description")
                    );
                    return Optional.of(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Product> findAllList() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM products";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("product_name"),
                            resultSet.getInt("category_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("product_image"),
                            resultSet.getLong("product_price"),
                            resultSet.getString("product_description")
                    );
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO products VALUES (?, ?, ?, ?, ?. ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getProductId());
            preparedStatement.setInt(2, product.getCategoryId());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setString(4, product.getProductImage());
            preparedStatement.setLong(5, product.getProductPrice());
            preparedStatement.setString(6, product.getDescription());

            return preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            return preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE products SET category_id = ?, product_name = ?, product_image = ?, product_price = ?, description = ? WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductImage());
            preparedStatement.setLong(4, product.getProductPrice());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getProductId());

            return preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
