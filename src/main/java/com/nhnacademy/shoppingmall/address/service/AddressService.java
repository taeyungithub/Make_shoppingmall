package com.nhnacademy.shoppingmall.address.service;

import com.nhnacademy.shoppingmall.address.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Optional<Address> getAddressById(int addressId);

    List<Address> getAddressesByUserId(String userId);

    void addAddress(Address address);

    void deleteAddress(int addressId);

    void updateAddress(Address address);
}
