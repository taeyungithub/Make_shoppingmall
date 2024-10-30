package com.nhnacademy.shoppingmall.category.service;

import com.nhnacademy.shoppingmall.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> getCategoryById(int categoryId);

    List<Category> getAllCategories();

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int categoryId);
}
