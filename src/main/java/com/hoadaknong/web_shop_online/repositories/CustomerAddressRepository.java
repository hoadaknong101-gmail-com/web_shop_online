package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.CustomerAddress;
import com.hoadaknong.web_shop_online.entities.keys.CustomerAddressKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, CustomerAddressKey> {
}
