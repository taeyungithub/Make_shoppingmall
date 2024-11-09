package com.shoppingmall.service;

import com.shoppingmall.domain.Address;
import com.shoppingmall.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> getAddressById(int addressId) {
        return addressRepository.findById(addressId);
    }

    public List<Address> getAddressesByUserId(String userId) {
        return addressRepository.findAllByUser_UserId(userId);
    }

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public void deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
    }

    @Transactional
    public void updateAddress(Address updatedAddress) {
        Optional<Address> optionalAddress = addressRepository.findById(updatedAddress.getAddressId());
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setAddress(updatedAddress.getAddress());
            addressRepository.save(existingAddress);
        } else {
            throw new RuntimeException("Address not found with ID: " + updatedAddress.getAddressId());
        }
    }
}
