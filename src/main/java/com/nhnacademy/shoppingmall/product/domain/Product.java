package com.nhnacademy.shoppingmall.product.domain;


public class Product {
    private int productId;
    private int categoryId;
    private String productName;
    private String productImage;
    private long productPrice;
    private String description;

    public Product() {}

    public Product(int productId, int categoryId, String productName, String productImage, long cost, String description) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = cost;
        this.description = description;
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

}
