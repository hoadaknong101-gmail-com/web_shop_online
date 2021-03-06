package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.entities.ProductBrand;
import com.hoadaknong.web_shop_online.entities.ProductCategory;
import com.hoadaknong.web_shop_online.repositories.ProductBrandRepository;
import com.hoadaknong.web_shop_online.repositories.ProductCategoryRepository;
import com.hoadaknong.web_shop_online.repositories.ProductRepository;
import com.hoadaknong.web_shop_online.services.StatsService;
import com.hoadaknong.web_shop_online.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/web_shop/admin/products")
public class AdminProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository categoryRepository;

    @Autowired
    ProductBrandRepository brandRepository;

    @Autowired
    UploadFileService storageFile;

    @Autowired
    StatsService statsService;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Integer page = 1;

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
        p.setModifiedDate(new Date());
        productRepository.save(p);
        System.out.println(new Date() + " INFO --- Insert new product successfully!");
        redirectAttributes.addFlashAttribute("message","Th??m s???n ph???m m???i th??nh c??ng");

        return "redirect:/web_shop/admin/products";
    }
    @RequestMapping ("/update")
    public String updateProduct(Product p,
                                   @RequestParam("categoryId") Integer categoryId,
                                   @RequestParam("productBrand") Integer productBrand,
                                   @RequestParam("image") MultipartFile file,
                                   RedirectAttributes redirectAttributes,
                                Model model){
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
        redirectAttributes.addFlashAttribute("message","C???p nh???t s???n ph???m th??nh c??ng");
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
        double profitValue = statsService.getProfitUpToNow();

        model.addAttribute("profitValue", String.format("%,.0f", profitValue));
        model.addAttribute("listBrand",listBrand);
        model.addAttribute("listCategory", listCategory);
        model.addAttribute("product",getProduct);
        model.addAttribute("page",page);
        return "admin_page/admin_product_product_update_product";
    }


    @RequestMapping(value="/brands/update/{brandID}")
    public String updateProductBrand(@PathVariable Integer brandID,Model model){

        ProductBrand brand = brandRepository.getById(brandID);
        double profitValue = statsService.getProfitUpToNow();

        model.addAttribute("profitValue", String.format("%,.0f", profitValue));
        model.addAttribute("brand",brand);
        model.addAttribute("title","C???p nh???t th????ng hi???u");
        model.addAttribute("_action","C???p nh???t");
        model.addAttribute("page",page);
        return "admin_page/admin_product_brand_form";
    }

    @RequestMapping(value ="/brands/save")
    public String saveProductBrand(ProductBrand b, RedirectAttributes attributes){
        brandRepository.save(b);
        attributes.addFlashAttribute("message","L??u th??nh c??ng");
        return "redirect:/web_shop/admin/products/brands";
    }

    @RequestMapping(value="/brands/delete/{id}")
    public String deleteProductBrand(@PathVariable Integer id, RedirectAttributes attributes){
        brandRepository.deleteById(id);
        attributes.addFlashAttribute("message","X??a th??nh c??ng");
        return "redirect:/web_shop/admin/products/brands";
    }


    @RequestMapping(value = "/categories")
    public String categoriesPage(Model model){
        List<ProductCategory> listCat = categoryRepository.findAll();
        Integer amount = listCat.size();
        double profitValue = statsService.getProfitUpToNow();

        model.addAttribute("profitValue", String.format("%,.0f", profitValue));
        model.addAttribute("listCategory",listCat);
        model.addAttribute("amount",amount);
        model.addAttribute("page",page);
        return "admin_page/admin_product_category";
    }

    @RequestMapping(value="/categories/update/{id}")
    public String updateProductCategory(@PathVariable Integer id,Model model){
        ProductCategory c = categoryRepository.getById(id);
        double profitValue = statsService.getProfitUpToNow();

        model.addAttribute("profitValue", String.format("%,.0f", profitValue));
        model.addAttribute("category",c);
        model.addAttribute("title","C???p nh???t lo???i s???n ph???m");
        model.addAttribute("_action","C???p nh???t");
        model.addAttribute("page",page);
        return "admin_page/admin_product_category_form";
    }

    @RequestMapping(value = "/categories/save")
    public String saveCategory(ProductCategory c, RedirectAttributes attributes){
        categoryRepository.save(c);
        attributes.addFlashAttribute("message","L??u th??nh c??ng!");

        return "redirect:/web_shop/admin/products/categories";
    }

    @RequestMapping(value="/categories/delete/{id}")
    public String deleteCategory(@PathVariable Integer id, RedirectAttributes attributes){
        categoryRepository.deleteById(id);

        attributes.addFlashAttribute("message","X??a th??nh c??ng!");

        return "redirect:/web_shop/admin/products/categories";
    }
}
