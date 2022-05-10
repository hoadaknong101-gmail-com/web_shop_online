package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByName (String name);
    List<Product> findFirstByCategoryId(ProductCategory id);
    List<Product> findTop6ByCategoryId(ProductCategory id);
}
