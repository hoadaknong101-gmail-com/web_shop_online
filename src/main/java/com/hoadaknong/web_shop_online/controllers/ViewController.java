package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductBrand;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import com.hoadaknong.web_shop_online.repositories.ProductBrandRepository;
import com.hoadaknong.web_shop_online.repositories.ProductCategoryRepository;
import com.hoadaknong.web_shop_online.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ViewController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository categoryRepository;

    @Autowired
    ProductBrandRepository brandRepository;

    // Home page


    // Product pages

    @RequestMapping(value = "/products")
    public String productPage(Model model){
        List<Product> productList = productRepository.findAll();
        int amount = productList.size();
        model.addAttribute("listProducts",productList);
        model.addAttribute("amount",amount);
        return "admin_product_product";
    }


    @RequestMapping(value = "/products/new")
    public String addProductPage(Model model){
        Product product = new Product();
        List<ProductCategory> listCategory = categoryRepository.findAll();
        List<ProductBrand> listBrand = brandRepository.findAll();

        model.addAttribute("product",product);
        model.addAttribute("listBrand",listBrand);
        model.addAttribute("listCategory", listCategory);

        return "admin_product_product_add_product";
    }
    @RequestMapping(value ="/products/brands")
    public String brandPage(Model model){
        List<ProductBrand> listBrand = brandRepository.findAll();
        Integer amount = listBrand.size();

        model.addAttribute("listBrand",listBrand);
        model.addAttribute("amount",amount);

        return "admin_product_brand";
    }
    @RequestMapping(value ="/products/brands/new")
    public String newBrand(Model model){
        ProductBrand b = new ProductBrand();

        model.addAttribute("title","Thêm thương hiệu");
        model.addAttribute("_action","Thêm");
        model.addAttribute("brand",b);

        return "admin_product_brand_form";
    }

    @RequestMapping(value = "/products/categories/new")
    public String newCategory(Model model){
        ProductCategory c = new ProductCategory();

        model.addAttribute("category",c);
        model.addAttribute("title","Thêm loại sản phẩm");
        model.addAttribute("_action","Thêm");

        return "admin_product_category_form";
    }

    // Customer
}
