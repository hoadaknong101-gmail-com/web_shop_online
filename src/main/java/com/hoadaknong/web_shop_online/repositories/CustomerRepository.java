package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public List<Customer> findCustomerByFirstName(String name);
}
