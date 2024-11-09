package com.shoppingmall.repository;

import com.shoppingmall.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    // 특정 사용자 ID로 모든 주소 조회
    List<Address> findAllByUser_UserId(String userId);

}
