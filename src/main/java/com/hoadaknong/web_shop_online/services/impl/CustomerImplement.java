package com.hoadaknong.web_shop_online.services.impl;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Role;
import com.hoadaknong.web_shop_online.repositories.CustomerRepository;
import com.hoadaknong.web_shop_online.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImplement implements CustomerService {

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

    @Override
    public List<Customer> findCustomerByRole(Role role) {
        return rp.findCustomersByRole(role);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return rp.getById(id);
    }

    @Override
    public Customer getCustomerByEmailAndPassword(String email, String password) {
        return rp.getCustomerByEmailAndAndPassword(email,password);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return rp.getCustomerByEmail(email);
    }
}
