package com.nhnacademy.shoppingmall.product.domain;


public class Product {
    private int productId;          // mysql 자동증가
    private int categoryId;
    private String productName;
    private String productImage;
    private long productPrice;
    private String description;
    private int stock;

    public Product() {
    }

    public Product(int categoryId, String productName, String productImage, long cost, String description, int stock) {
        this.categoryId = categoryId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = cost;
        this.description = description;
        this.stock = stock;
    }

    public Product(int productId, int categoryId, String productName, String productImage, long productPrice, String description, int stock) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;

    }

    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = (long) productPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

}
