package com.hoadaknong.web_shop_online.services;

import com.hoadaknong.web_shop_online.entities.Address;
import com.hoadaknong.web_shop_online.entities.CustomerAddress;
import com.hoadaknong.web_shop_online.entities.keys.CustomerAddressKey;
import com.hoadaknong.web_shop_online.repositories.AddressRepository;
import com.hoadaknong.web_shop_online.repositories.CustomerAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressImplement implements AddressService{

    @Autowired
    private AddressRepository rpAddress;

    @Autowired
    private CustomerAddressRepository rpAddresOfCustomer;

    @Override
    public void saveAddress(Address address) {
        rpAddress.save(address);
    }

    @Override
    public List<Address> findAllAddress() {
        return rpAddress.findAll();
    }

    @Override
    public void deleteAddressById(Integer id) {
        rpAddress.deleteById(id);
    }

    @Override
    public Optional<Address> findAddressById(Integer id) {
        return rpAddress.findById(id);
    }

    @Override
    public List<Address> findAddressesByAddressLine(String addressLine) {
        return rpAddress.findAddressesByAddressLine(addressLine);
    }

    @Override
    public List<Address> findAddressesByDistrict(String district) {
        return rpAddress.findAddressesByDistrict(district);
    }

    @Override
    public List<Address> findAddressesByPostalCode(String code) {
        return rpAddress.findAddressesByPostalCode(code);
    }

    @Override
    public void saveAddressOfCustomer(CustomerAddress address) {
        rpAddresOfCustomer.save(address);
    }

    @Override
    public void deleteAddressOfCustomer(Integer idAddress, Integer idCustomer) {
        CustomerAddressKey key = new CustomerAddressKey();
        key.setAddressId(idAddress);
        key.setCustomerId(idCustomer);
        rpAddresOfCustomer.deleteById(key);
    }
}
