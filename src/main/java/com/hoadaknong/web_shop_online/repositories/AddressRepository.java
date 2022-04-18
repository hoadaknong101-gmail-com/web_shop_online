package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAddressesByAddressLine(String addressLine);
    List<Address> findAddressesByDistrict(String district);
    List<Address> findAddressesByPostalCode(String code);
}
