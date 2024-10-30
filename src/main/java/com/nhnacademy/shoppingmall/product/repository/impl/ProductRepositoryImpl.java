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
                            resultSet.getString("description"),
                            resultSet.getInt("stock")
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
                            resultSet.getString("description"),
                            resultSet.getInt("stock")
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
        String sql = "INSERT INTO products (category_id, product_name, product_image, product_price, description, stock) VALUES (?, ?, ?, ?, ?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductImage());
            preparedStatement.setLong(4, product.getProductPrice());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getStock());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE products SET category_id = ?, product_name = ?, product_image = ?, product_price = ?, description = ? ,stock = ? WHERE product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, product.getCategoryId());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductImage());
            preparedStatement.setLong(4, product.getProductPrice());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getStock());
            preparedStatement.setInt(7, product.getProductId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Product> findByName(String productName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM products WHERE product_name LIKE ?";
        List<Product> productList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + productName + "%");

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    productList.add(new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getString("product_image"),
                            rs.getLong("product_price"),
                            rs.getString("description"),
                            rs.getInt("stock")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("상품 검색 실패", e);
        }

        return productList;
    }


    @Override
    public long totalCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT count(*) FROM products";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    @Override
    public Page<Product> findAll(int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        ResultSet rs = null;
        String sql = "select * from products order by product_id desc limit  ?,? ";
        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, offset);
            psmt.setInt(2, limit);
            rs = psmt.executeQuery();
            List<Product> productList = new ArrayList<>(pageSize);

            while (rs.next()) {
                productList.add(
                        new Product(
                                rs.getInt("product_id"),
                                rs.getInt("category_id"),
                                rs.getString("product_name"),
                                rs.getString("product_image"),
                                rs.getLong("product_price"),
                                rs.getString("description"),
                                rs.getInt("stock")
                        )
                );
            }

            long total = 0;

            if (!productList.isEmpty()) {
                total = totalCount();
            }

            return new Page<Product>(productList, total);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Product> findByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM products WHERE category_id = ?";
        List<Product> products = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, categoryId);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getString("product_image"),
                            rs.getLong("product_price"),
                            rs.getString("description"),
                            rs.getInt("stock")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    @Override
    public Page<Product> findAllByCategory(int categoryId, int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM products WHERE category_id = ? ORDER BY product_id DESC LIMIT ?, ?";

        int offset = (page - 1) * pageSize;
        List<Product> products = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, categoryId);
            psmt.setInt(2, offset);
            psmt.setInt(3, pageSize);

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getInt("category_id"),
                            rs.getString("product_name"),
                            rs.getString("product_image"),
                            rs.getLong("product_price"),
                            rs.getString("description"),
                            rs.getInt("stock")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        long totalCount = countByCategoryId(categoryId);
        return new Page<>(products, totalCount);
    }

    // 카테고리별 상품 개수 계산 메서드
    private long countByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM products WHERE category_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, categoryId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
