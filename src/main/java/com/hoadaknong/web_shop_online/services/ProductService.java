package com.hoadaknong.web_shop_online.services;

import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductBrand;
import com.hoadaknong.web_shop_online.entities.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService{
    // Product
    Optional<Product> findProductById(Integer id);
    List<Product> findAllProduct();
    void deleteProductById(Integer id);
    List<Product> findProductByName(String name);
    void saveProduct(Product product);

    // Brand
    Optional<ProductBrand> findBrandById(Integer id);
    List<ProductBrand> findAllBrand();
    void deleteBrandById(Integer id);
    void saveBrand(ProductBrand brand);

    // Category
    Optional<ProductCategory> findCategoryById(Integer id);
    List<ProductCategory> findAllCategory();
    void deleteCategoryById(Integer id);
    void saveCategory(ProductCategory category);
}
