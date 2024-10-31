package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryImplTest {

    private final ProductRepository productRepository = new ProductRepositoryImpl();
    private Product testProduct;

    @BeforeEach
    void setUp() throws SQLException {
        DbConnectionThreadLocal.initialize();
        testProduct = new Product(1, "Test Product", "", 10000L, "Test Description", 10);
        productRepository.save(testProduct);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DbConnectionThreadLocal.setSqlError(true);
        DbConnectionThreadLocal.reset();
    }

    @Test
    @Order(1)
    @DisplayName("Product 조회 by ID")
    void findById() {
        Optional<Product> foundProduct = productRepository.findById(testProduct.getProductId());
        assertTrue(foundProduct.isPresent());
        assertEquals(testProduct.getProductName(), foundProduct.get().getProductName());
    }

    @Test
    @Order(2)
    @DisplayName("모든 Products 조회")
    void findAllList() {
        List<Product> products = productRepository.findAllList();
        assertFalse(products.isEmpty());
    }

    @Test
    @Order(3)
    @DisplayName("Product 저장")
    void save() {
        Product newProduct = new Product(0, 2, "New Product", "new_image.jpg", 20000L, "New Description", 20);
        int result = productRepository.save(newProduct);
        assertEquals(1, result);
        productRepository.deleteByProductId(newProduct.getProductId());
    }

    @Test
    @Order(4)
    @DisplayName("Product 삭제 by ID")
    void deleteByProductId() {
        int result = productRepository.deleteByProductId(testProduct.getProductId());
        assertEquals(1, result);
        assertFalse(productRepository.findById(testProduct.getProductId()).isPresent());
    }

    @Test
    @Order(5)
    @DisplayName("Product 수정")
    void update() {
        testProduct.setProductName("Updated Product");
        int result = productRepository.update(testProduct);
        assertEquals(1, result);

        Optional<Product> updatedProduct = productRepository.findById(testProduct.getProductId());
        assertTrue(updatedProduct.isPresent());
        assertEquals("Updated Product", updatedProduct.get().getProductName());
    }

    @Test
    @Order(6)
    @DisplayName("Find Product by Category ID")
    void findByCategoryId() {
        List<Product> products = productRepository.findByCategoryId(testProduct.getCategoryId());
        assertFalse(products.isEmpty());
        assertEquals(testProduct.getCategoryId(), products.get(0).getCategoryId());
    }

    @Test
    @Order(7)
    @DisplayName("Total Count of Products")
    void totalCount() {
        long count = productRepository.totalCount();
        assertTrue(count > 0);
    }
}
