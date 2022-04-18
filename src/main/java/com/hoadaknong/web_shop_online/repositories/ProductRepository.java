package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName (String name);
}
