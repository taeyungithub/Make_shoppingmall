package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> getAllProdcutList() {
        return productRepository.findAllList();
    }

    @Override
    public void saveProduct(Product product) {
        if (getProduct(product.getProductId()) != null) {
            throw new ProductAlreadyExistsException(product.getProductName());
        }
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        if (getProduct(product.getProductId()) == null) {
            throw new ProductNotFoundException(product.getProductName());
        }
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        if (getProduct(productId) == null) {
            throw new ProductNotFoundException(String.valueOf(productId));
        }
        productRepository.deleteByProductId(productId);
    }

    @Override
    public List<Product> searchProductsByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public Page<Product> getProductPageList(int pageSize, int currentPage) {
        Optional<Page<Product>> pageProducts = Optional.ofNullable(productRepository.findAll(currentPage, pageSize));

        if (!pageProducts.isPresent()) {
            throw new RuntimeException("상품들을 불러오는데 실패했습니다.");
        }

        return pageProducts.get();
    }
}
