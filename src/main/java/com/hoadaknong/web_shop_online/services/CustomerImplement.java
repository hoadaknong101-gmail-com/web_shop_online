package com.hoadaknong.web_shop_online.services;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImplement implements CustomerService{

    @Autowired
    private CustomerRepository rp;

    @Override
    public void saveCustomer(Customer customer) {
        rp.save(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(Integer id) {
        return rp.findById(id);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return rp.findAll();
    }

    @Override
    public void deleteCustomerById(Integer id) {
        rp.deleteById(id);
    }
}
