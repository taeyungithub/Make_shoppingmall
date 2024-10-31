package com.nhnacademy.shoppingmall.address.repository.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public Optional<Address> findById(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM addresses WHERE address_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, addressId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Address(
                            rs.getInt("address_id"),
                            rs.getString("address"),
                            rs.getString("user_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Address> findAllByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM addresses WHERE user_id = ?";
        List<Address> addresses = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    addresses.add(new Address(
                            rs.getInt("address_id"),
                            rs.getString("address"),
                            rs.getString("user_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;
    }

    @Override
    public int save(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO addresses (address_id, address, user_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getUserId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteById(int addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM addresses WHERE address_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, addressId);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateAddress(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE addresses SET address = ? WHERE address_id = ? AND user_id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setInt(2, address.getAddressId());
            preparedStatement.setString(3, address.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("주소 업데이트 중 오류가 발생했습니다.", e);
        }
    }
}
