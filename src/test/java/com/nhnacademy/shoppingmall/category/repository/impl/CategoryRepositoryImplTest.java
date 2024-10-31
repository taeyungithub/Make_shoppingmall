package com.nhnacademy.shoppingmall.category.repository.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryRepositoryImplTest {
    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    private Category testCategory;

    @BeforeEach
    void setUp() throws SQLException {
        DbConnectionThreadLocal.initialize();

        testCategory = new Category(1, "Test Category");
        categoryRepository.save(testCategory);
    }

    @AfterEach
    void tearDown() throws SQLException {
        categoryRepository.deleteById(testCategory.getCategoryId());
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("Category 조회 by ID")
    void findById() {
        Optional<Category> foundCategory = categoryRepository.findById(testCategory.getCategoryId());

        assertTrue(foundCategory.isPresent());
        assertEquals(testCategory.getCategoryName(), foundCategory.get().getCategoryName());
    }

    @Test
    @DisplayName("모든 Categories 조회")
    void findAll() {
        List<Category> categories = categoryRepository.findAll();

        assertFalse(categories.isEmpty());
        assertTrue(categories.stream().anyMatch(category -> category.getCategoryId() == testCategory.getCategoryId()));
    }

    @Test
    @DisplayName("Category 저장")
    void save() {
        Category newCategory = new Category(2, "New Category");

        int result = categoryRepository.save(newCategory);
        assertEquals(1, result);

        Optional<Category> foundCategory = categoryRepository.findById(newCategory.getCategoryId());
        assertTrue(foundCategory.isPresent());
        assertEquals(newCategory.getCategoryName(), foundCategory.get().getCategoryName());

        categoryRepository.deleteById(newCategory.getCategoryId());
    }

    @Test
    @DisplayName("Category 수정")
    void update() {
        testCategory.setCategoryName("Updated Category");

        int result = categoryRepository.update(testCategory);
        assertEquals(1, result);

        Optional<Category> updatedCategory = categoryRepository.findById(testCategory.getCategoryId());
        assertTrue(updatedCategory.isPresent());
        assertEquals("Updated Category", updatedCategory.get().getCategoryName());
    }

    @Test
    @DisplayName("Category 삭제 by ID")
    void deleteById() {
        int result = categoryRepository.deleteById(testCategory.getCategoryId());
        assertEquals(1, result);

        Optional<Category> foundCategory = categoryRepository.findById(testCategory.getCategoryId());
        assertFalse(foundCategory.isPresent());
    }
}
