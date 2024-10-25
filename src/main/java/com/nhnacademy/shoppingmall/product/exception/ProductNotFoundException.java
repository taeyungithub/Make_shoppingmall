package com.nhnacademy.shoppingmall.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productName) {
        super(String.format("user not found:" + productName));
    }
}
