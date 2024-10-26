package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(int productId);
    List<Product> findAllList();
    int save(Product product);
    int deleteByProductId(int productId);
    int update(Product product);

    long totalCount();     //totalCount 전체 row 갯수를 구합니다.
    Page<Product> findAll(int page, int pageSize);   //페이징처리된 결과를 반환 합니다.
}
