package com.hoadaknong.web_shop_online.services;


import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Role;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void saveCustomer(Customer customer);
    Optional<Customer> findCustomerById(Integer id);
    List<Customer> findAllCustomer();
    void deleteCustomerById(Integer id);
    List<Customer> findCustomerByRole(Role role);
}
