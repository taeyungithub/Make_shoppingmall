package com.nhnacademy.shoppingmall.address.repository;

import com.nhnacademy.shoppingmall.address.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    Optional<Address> findById(int addressId);
    List<Address> findAllByUserId(String userId);
    int save(Address address);
    int deleteById(int addressId);
    void updateAddress(Address address);

}
