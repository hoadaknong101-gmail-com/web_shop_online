package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
