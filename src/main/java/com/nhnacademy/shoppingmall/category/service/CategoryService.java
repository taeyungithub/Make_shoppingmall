package com.nhnacademy.shoppingmall.category.service;

import com.nhnacademy.shoppingmall.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> getCategoryById(int categoryId);

    List<Category> getAllCategories();

    int addCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(int categoryId);
}
