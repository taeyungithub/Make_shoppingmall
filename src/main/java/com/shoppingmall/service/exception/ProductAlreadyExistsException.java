package com.shoppingmall.service.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String productName) {
        super(String.format("product already exist:%s", productName));
    }
}
