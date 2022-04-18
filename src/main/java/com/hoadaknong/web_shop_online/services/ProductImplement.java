package com.hoadaknong.web_shop_online.services;

import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductBrand;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import com.hoadaknong.web_shop_online.repositories.ProductBrandRepository;
import com.hoadaknong.web_shop_online.repositories.ProductCategoryRepository;
import com.hoadaknong.web_shop_online.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImplement implements ProductService{

    @Autowired
    private ProductRepository rpProduct;

    @Autowired
    private ProductBrandRepository rpBrand;

    @Autowired
    private ProductCategoryRepository rpCategory;

    // Product
    @Override
    public Optional<Product> findProductById(Integer id) {
        return rpProduct.findById(id);
    }

    @Override
    public List<Product> findAllProduct() {
        return rpProduct.findAll();
    }

    @Override
    public void deleteProductById(Integer id) {
        rpProduct.deleteById(id);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return rpProduct.findByName(name);
    }

    @Override
    public void saveProduct(Product product) {
        rpProduct.save(product);
    }

    // Brand
    @Override
    public Optional<ProductBrand> findBrandById(Integer id) {
        return rpBrand.findById(id);
    }

    @Override
    public List<ProductBrand> findAllBrand() {
        return rpBrand.findAll();
    }

    @Override
    public void deleteBrandById(Integer id) {
        rpBrand.deleteById(id);
    }

    @Override
    public void saveBrand(ProductBrand brand) {
        rpBrand.save(brand);
    }

    // Category
    @Override
    public Optional<ProductCategory> findCategoryById(Integer id) {
        return rpCategory.findById(id);
    }

    @Override
    public List<ProductCategory> findAllCategory() {
        return rpCategory.findAll();
    }

    @Override
    public void deleteCategoryById(Integer id) {
        rpCategory.deleteById(id);
    }

    @Override
    public void saveCategory(ProductCategory category) {
        rpCategory.save(category);
    }
}
