package com.hoadaknong.web_shop_online.services;

import com.hoadaknong.web_shop_online.entities.Address;
import com.hoadaknong.web_shop_online.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressImplement implements AddressService{

    @Autowired
    private AddressRepository rp;

    @Override
    public void saveAddress(Address address) {
        rp.save(address);
    }

    @Override
    public List<Address> findAllAddress() {
        return rp.findAll();
    }

    @Override
    public void deleteAddressById(Integer id) {
        rp.deleteById(id);
    }

    @Override
    public Optional<Address> findAddressById(Integer id) {
        return rp.findById(id);
    }

    @Override
    public List<Address> findAddressesByAddressLine(String addressLine) {
        return rp.findAddressesByAddressLine(addressLine);
    }

    @Override
    public List<Address> findAddressesByDistrict(String district) {
        return rp.findAddressesByDistrict(district);
    }

    @Override
    public List<Address> findAddressesByPostalCode(String code) {
        return rp.findAddressesByPostalCode(code);
    }
}
