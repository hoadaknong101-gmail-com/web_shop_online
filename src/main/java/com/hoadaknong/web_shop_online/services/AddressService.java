package com.hoadaknong.web_shop_online.services;

import com.hoadaknong.web_shop_online.entities.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    void saveAddress(Address address);
    List<Address> findAllAddress();
    void deleteAddressById(Integer id);
    Optional<Address> findAddressById(Integer id);
    List<Address> findAddressesByAddressLine(String addressLine);
    List<Address> findAddressesByDistrict(String district);
    List<Address> findAddressesByPostalCode(String code);
}
