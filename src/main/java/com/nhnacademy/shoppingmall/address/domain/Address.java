package com.nhnacademy.shoppingmall.address.domain;

// 주소
public class Address {
    int addressId;
    String address;
    String userId;

    public Address(int addressId, String address, String userId) {
        this.addressId = addressId;
        this.address = address;
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public String getUserId() {
        return userId;
    }
}
