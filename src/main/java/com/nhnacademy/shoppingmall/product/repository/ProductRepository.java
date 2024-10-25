package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(int productId);
    List<Product> findAllList();
    int save(Product product);
    int deleteByProductId(int productId);
    int update(Product product);

}
