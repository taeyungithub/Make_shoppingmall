package com.nhnacademy.shoppingmall.address.service.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    private AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
    private AddressServiceImpl addressService = new AddressServiceImpl(addressRepository);
    private Address testAddress = new Address(1, "Test Address", "test-user");

    @Test
    @DisplayName("Address 얻기 by ID")
    void getAddressById() {
        when(addressRepository.findById(anyInt())).thenReturn(Optional.of(testAddress));

        Optional<Address> foundAddress = addressService.getAddressById(testAddress.getAddressId());

        assertTrue(foundAddress.isPresent());
        assertEquals(testAddress.getUserId(), foundAddress.get().getUserId());
        verify(addressRepository, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("모든 Addresses 얻기 by User ID")
    void getAddressesByUserId() {
        when(addressRepository.findAllByUserId(anyString())).thenReturn(Arrays.asList(testAddress));

        List<Address> addresses = addressService.getAddressesByUserId(testAddress.getUserId());

        assertFalse(addresses.isEmpty());
        assertEquals(1, addresses.size());
        assertEquals(testAddress.getUserId(), addresses.get(0).getUserId());
        verify(addressRepository, times(1)).findAllByUserId(anyString());
    }

    @Test
    @DisplayName("Address 추가")
    void addAddress() {
        when(addressRepository.save(any(Address.class))).thenReturn(1);

        addressService.addAddress(testAddress);

        verify(addressRepository, times(1)).save(testAddress);
    }

    @Test
    @DisplayName("Address 삭제 by ID")
    void deleteAddress() {
        when(addressRepository.deleteById(anyInt())).thenReturn(1);

        addressService.deleteAddress(testAddress.getAddressId());

        verify(addressRepository, times(1)).deleteById(anyInt());
    }

    @Test
    @DisplayName("Address 수정")
    void updateAddress() {
        doNothing().when(addressRepository).updateAddress(any(Address.class));

        testAddress.setAddress("Updated Address");
        addressService.updateAddress(testAddress);

        verify(addressRepository, times(1)).updateAddress(testAddress);
    }
}
