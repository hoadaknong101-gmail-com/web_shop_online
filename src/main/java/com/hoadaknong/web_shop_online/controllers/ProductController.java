package com.hoadaknong.web_shop_online.controllers;


import com.hoadaknong.web_shop_online.entities.Feedback;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.services.FeedbackService;
import com.hoadaknong.web_shop_online.services.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    
    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/product/single_product/{id}")
    public String singlePageProduct(@PathVariable Integer id, Model model){
        Product product = productService.findProductById(id);
        List<Product> top6ProductRelate = productService.findTop6ProductByCategoryId(product.getCategoryId().getId());
        List<Feedback> listFeedbacks = feedbackService.findFeedbackByProductId(id);
        
        model.addAttribute("listFeedback", listFeedbacks);
        model.addAttribute("productRelate", top6ProductRelate);
        model.addAttribute("product",product);
        
        return "client_page/single_product";
    }
}
