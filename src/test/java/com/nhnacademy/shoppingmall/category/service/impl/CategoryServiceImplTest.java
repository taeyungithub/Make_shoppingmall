package com.nhnacademy.shoppingmall.category.service.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
    private CategoryServiceImpl categoryService =  new CategoryServiceImpl(categoryRepository);
    private Category testCategory = new Category(1, "Test Category");


    @Test
    @DisplayName("카테고리 조회 by ID - 성공")
    void getCategoryById_Success() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(testCategory));

        Optional<Category> category = categoryService.getCategoryById(testCategory.getCategoryId());

        assertTrue(category.isPresent());
        assertEquals(testCategory.getCategoryName(), category.get().getCategoryName());
        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("카테고리 조회 by ID - 실패")
    void getCategoryById_Fail() {
        when(categoryRepository.findById(anyInt())).thenReturn(Optional.empty());

        Optional<Category> category = categoryService.getCategoryById(testCategory.getCategoryId());

        assertFalse(category.isPresent());
        verify(categoryRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("모든 카테고리 조회")
    void getAllCategories() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(testCategory));

        List<Category> categories = categoryService.getAllCategories();

        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals(testCategory.getCategoryName(), categories.get(0).getCategoryName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("카테고리 저장")
    void saveCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(1);

        int result = categoryService.addCategory(testCategory);

        assertEquals(1, result);
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    @DisplayName("카테고리 업데이트")
    void updateCategory() {
        when(categoryRepository.update(any(Category.class))).thenReturn(1);

        int result = categoryService.updateCategory(testCategory);

        assertEquals(1, result);
        verify(categoryRepository, times(1)).update(any(Category.class));
    }

    @Test
    @DisplayName("카테고리 삭제 by ID")
    void deleteCategoryById() {
        when(categoryRepository.deleteById(anyInt())).thenReturn(1);

        int result = categoryService.deleteCategory(testCategory.getCategoryId());

        assertEquals(1, result);
        verify(categoryRepository, times(1)).deleteById(anyInt());
    }
}
