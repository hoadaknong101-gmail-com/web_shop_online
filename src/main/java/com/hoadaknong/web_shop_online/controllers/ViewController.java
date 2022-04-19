package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductBrand;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import com.hoadaknong.web_shop_online.repositories.ProductBrandRepository;
import com.hoadaknong.web_shop_online.repositories.ProductCategoryRepository;
import com.hoadaknong.web_shop_online.repositories.ProductRepository;
import com.hoadaknong.web_shop_online.services.CustomerService;
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

    @Autowired
    CustomerService customerService;


    // Sign in & Sign up form

    @RequestMapping(value = "/sign_in_sign_up")
    public String signInSignUpPag(Model model){
        Customer c = new Customer();
        model.addAttribute("customer",c);

        return "sign_in_sign_up_form";
    }

    // Home page


    // Product pages

    @RequestMapping(value = "/web_shop/admin/products")
    public String productPage(Model model){
        List<Product> productList = productRepository.findAll();
        int amount = productList.size();
        model.addAttribute("listProducts",productList);
        model.addAttribute("amount",amount);
        return "admin_product_product";
    }


    @RequestMapping(value = "/web_shop/admin/products/new")
    public String addProductPage(Model model){
        Product product = new Product();
        List<ProductCategory> listCategory = categoryRepository.findAll();
        List<ProductBrand> listBrand = brandRepository.findAll();

        model.addAttribute("product",product);
        model.addAttribute("listBrand",listBrand);
        model.addAttribute("listCategory", listCategory);

        return "admin_product_product_add_product";
    }
    @RequestMapping(value ="/web_shop/admin/products/brands")
    public String brandPage(Model model){
        List<ProductBrand> listBrand = brandRepository.findAll();
        Integer amount = listBrand.size();

        model.addAttribute("listBrand",listBrand);
        model.addAttribute("amount",amount);

        return "admin_product_brand";
    }
    @RequestMapping(value ="/web_shop/admin/products/brands/new")
    public String newBrand(Model model){
        ProductBrand b = new ProductBrand();

        model.addAttribute("title","Thêm thương hiệu");
        model.addAttribute("_action","Thêm");
        model.addAttribute("brand",b);

        return "admin_product_brand_form";
    }

    @RequestMapping(value = "/web_shop/admin/products/categories/new")
    public String newCategory(Model model){
        ProductCategory c = new ProductCategory();

        model.addAttribute("category",c);
        model.addAttribute("title","Thêm loại sản phẩm");
        model.addAttribute("_action","Thêm");

        return "admin_product_category_form";
    }

    // Customer

    @RequestMapping(value="/web_shop/admin/customers")
    public String adminCustomerPage(Model model){
        List<Customer> listCustomer = customerService.findAllCustomer();

        model.addAttribute("listCustomer", listCustomer);

        return "admin_customer";
    }
}
