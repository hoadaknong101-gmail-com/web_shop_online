package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.beans.ResponseObject;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductBrand;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import com.hoadaknong.web_shop_online.repositories.ProductBrandRepository;
import com.hoadaknong.web_shop_online.repositories.ProductCategoryRepository;
import com.hoadaknong.web_shop_online.repositories.ProductRepository;
import com.hoadaknong.web_shop_online.services.ProductService;
import com.hoadaknong.web_shop_online.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/web_shop/admin/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository categoryRepository;

    @Autowired
    ProductBrandRepository brandRepository;

    @Autowired
    UploadFileService storageFile;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RequestMapping ("/insert")
    public String insertNewProduct(Product p,
                                   @RequestParam("categoryId") Integer categoryId,
                                   @RequestParam("productBrand") Integer productBrand,
                                   @RequestParam("image") MultipartFile file,
                                   RedirectAttributes redirectAttributes){
        String filename = "";
        try{
            filename = storageFile.storeFile(file);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        ProductCategory category = categoryRepository.getById(categoryId);
        ProductBrand brand = brandRepository.getById(productBrand);
        p.setCategoryId(category);
        p.setProductBrand(brand);
        p.setThumbnailPhoto(filename);
        p.setRate(0.0);
        p.setModifiedDate(new Date());
        productRepository.save(p);
        System.out.println(new Date() + " INFO --- Insert new product successfully!");
        redirectAttributes.addFlashAttribute("message","Thêm sản phẩm mới thành công");
        return "redirect:/web_shop/admin/products";
    }
    @RequestMapping ("/update")
    public String updateProduct(Product p,
                                   @RequestParam("categoryId") Integer categoryId,
                                   @RequestParam("productBrand") Integer productBrand,
                                   @RequestParam("image") MultipartFile file,
                                   RedirectAttributes redirectAttributes){
        LocalDateTime now = LocalDateTime.now();
        String filename = p.getThumbnailPhoto();

        ProductCategory category = categoryRepository.getById(categoryId);
        ProductBrand brand = brandRepository.getById(productBrand);
        p.setCategoryId(category);
        p.setProductBrand(brand);
        try{
            p.setThumbnailPhoto(storageFile.storeFile(file));
        } catch(Exception e){
            System.out.println("");
        }
        p.setModifiedDate(new Date());
        productRepository.save(p);

        System.out.println(dtf.format(now).toString() + " INFO --- Update product successfully!");
        redirectAttributes.addFlashAttribute("message","Cập nhật sản phẩm thành công");
        return "redirect:/web_shop/admin/products";
    }

    @RequestMapping(value = "/delete/{productID}")
    public String deleteProduct(@PathVariable Integer productID, RedirectAttributes attributes){
        productRepository.deleteById(productID);
        attributes.addFlashAttribute("message","Delete product successfully!");
        return "redirect:/web_shop/admin/products";
    }

    @RequestMapping(value ="/update/{productID}")
    public String updateProduct(@PathVariable Integer productID, Model model){
        Product getProduct = productRepository.getById(productID);
        List<ProductCategory> listCategory = categoryRepository.findAll();
        List<ProductBrand> listBrand = brandRepository.findAll();

        model.addAttribute("listBrand",listBrand);
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("product",getProduct);

        return "admin_page/admin_product_product_update_product";
    }


    @RequestMapping(value="/brands/update/{brandID}")
    public String updateProductBrand(@PathVariable Integer brandID,Model model){

        ProductBrand brand = brandRepository.getById(brandID);

        model.addAttribute("brand",brand);
        model.addAttribute("title","Cập nhật thương hiệu");
        model.addAttribute("_action","Cập nhật");

        return "admin_page/admin_product_brand_form";
    }

    @RequestMapping(value ="/brands/save")
    public String saveProductBrand(ProductBrand b, RedirectAttributes attributes){
        brandRepository.save(b);
        attributes.addFlashAttribute("message","Lưu thành công");
        return "redirect:/web_shop/admin/products/brands";
    }

    @RequestMapping(value="/brands/delete/{id}")
    public String deleteProductBrand(@PathVariable Integer id, RedirectAttributes attributes){
        brandRepository.deleteById(id);
        attributes.addFlashAttribute("message","Xóa thành công");

        return "redirect:/web_shop/admin/products/brands";
    }


    @RequestMapping(value = "/categories")
    public String categoriesPage(Model model){
        List<ProductCategory> listCat = categoryRepository.findAll();
        Integer amount = listCat.size();

        model.addAttribute("listCategory",listCat);
        model.addAttribute("amount",amount);

        return "admin_page/admin_product_category";
    }

    @RequestMapping(value="/categories/update/{id}")
    public String updateProductCategory(@PathVariable Integer id,Model model){

        ProductCategory c = categoryRepository.getById(id);

        model.addAttribute("category",c);
        model.addAttribute("title","Cập nhật loại sản phẩm");
        model.addAttribute("_action","Cập nhật");

        return "admin_page/admin_product_category_form";
    }

    @RequestMapping(value = "/categories/save")
    public String saveCategory(ProductCategory c, RedirectAttributes attributes){
        categoryRepository.save(c);
        attributes.addFlashAttribute("message","Lưu thành công!");

        return "redirect:/web_shop/admin/products/categories";
    }

    @RequestMapping(value="/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes attributes){
        categoryRepository.deleteById(id);

        attributes.addFlashAttribute("message","Xóa thành công!");

        return "redirect:/web_shop/admin/products/categories";
    }
}
