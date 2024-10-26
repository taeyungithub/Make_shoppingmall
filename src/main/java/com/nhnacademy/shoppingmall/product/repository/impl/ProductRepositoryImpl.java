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
import java.util.Objects;
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
                            resultSet.getInt("product_id"),
                            resultSet.getInt("category_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("product_image"),
                            resultSet.getLong("product_price"),
                            resultSet.getString("description")
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
                            resultSet.getInt("product_id"),
                            resultSet.getInt("category_id"),
                            resultSet.getString("product_name"),
                            resultSet.getString("product_image"),
                            resultSet.getLong("product_price"),
                            resultSet.getString("description")
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
        String sql = "INSERT INTO products (category_id, product_name, product_image, product_price, description) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductImage());
            preparedStatement.setLong(4, product.getProductPrice());
            preparedStatement.setString(5, product.getDescription());

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

    @Override
    public long totalCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT count(*) FROM products";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            try(ResultSet rs = psmt.executeQuery()) {
                if(rs.next()){
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    @Override
    public Page<Product> findAll( int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page-1) * pageSize;
        int limit = pageSize;

        ResultSet rs = null;
        String sql="select * from products order by product_id desc limit  ?,? ";
        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1,offset);
            psmt.setInt(2,limit);
            rs= psmt.executeQuery();
            List<Product> productList = new ArrayList<>(pageSize);

            while(rs.next()){
                productList.add(
                        new Product(
                                rs.getInt("product_id"),
                                rs.getInt("category_id"),
                                rs.getString("product_name"),
                                rs.getString("product_image"),
                                rs.getLong("product_price"),
                                rs.getString("description")
                        )
                );
            }

            long total =0;

            if(!productList.isEmpty()){
                // size>0 조회 시도, 0이면 조회할 필요 없음, count query는 자원을 많이 소모하는 작업
                total = totalCount();
            }

            return  new Page<Product>(productList,total);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(Objects.nonNull(rs)){
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
