package com.nhnacademy.shoppingmall.address.repository.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressRepositoryImplTest {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final AddressRepository addressRepository = new AddressRepositoryImpl();

    private User testUser;
    private Address testAddress;

    @BeforeAll
    static void setupDatabase() throws SQLException {
        DbConnectionThreadLocal.initialize();
    }

    @BeforeEach
    void setUp() throws SQLException {
        testUser = new User("test-user", "Test User", "password", "19900101", User.Auth.ROLE_USER, 100_0000, LocalDateTime.now(), LocalDateTime.now());
        userRepository.save(testUser);

        testAddress = new Address(0, "Test Address", testUser.getUserId());
        addressRepository.save(testAddress);
    }

    @AfterEach
    void tearDown() throws SQLException {
        addressRepository.deleteById(testAddress.getAddressId());
        userRepository.deleteByUserId(testUser.getUserId());
    }

    @AfterAll
    static void closeDatabase() throws SQLException {
        DbConnectionThreadLocal.reset();
    }

    @Test
    @DisplayName("Address 조회 by ID")
    void findById() {
        Optional<Address> foundAddress = addressRepository.findById(testAddress.getAddressId());
        assertTrue(foundAddress.isPresent());
        assertEquals(testAddress.getUserId(), foundAddress.get().getUserId());
        assertEquals(testAddress.getAddress(), foundAddress.get().getAddress());
    }

    @Test
    @DisplayName("모든 Addresses 조회 by User ID")
    void findAllByUserId() {
        List<Address> addresses = addressRepository.findAllByUserId(testUser.getUserId());
        assertFalse(addresses.isEmpty());
        assertEquals(testUser.getUserId(), addresses.get(0).getUserId());
    }

    @Test
    @DisplayName("Address 저장")
    void save() {
        Address newAddress = new Address(0, "New Test Address", testUser.getUserId());

        int generatedId = addressRepository.save(newAddress);
        assertTrue(generatedId > 0);
        assertEquals(newAddress.getAddressId(), generatedId);

        addressRepository.deleteById(newAddress.getAddressId());
    }

    @Test
    @DisplayName("Address 삭제 by ID")
    void deleteById() {
        int result = addressRepository.deleteById(testAddress.getAddressId());
        assertEquals(1, result);

        Optional<Address> deletedAddress = addressRepository.findById(testAddress.getAddressId());
        assertFalse(deletedAddress.isPresent());
    }

    @Test
    @DisplayName("Address 수정")
    void updateAddress() {
        testAddress.setAddress("Updated Address");
        addressRepository.updateAddress(testAddress);

        Optional<Address> updatedAddress = addressRepository.findById(testAddress.getAddressId());
        assertTrue(updatedAddress.isPresent());
        assertEquals("Updated Address", updatedAddress.get().getAddress());
    }
}
