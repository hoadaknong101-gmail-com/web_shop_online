package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.FeedbackService;
import com.hoadaknong.web_shop_online.services.OrderService;
import com.hoadaknong.web_shop_online.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/web_shop/admin")
public class HomeAdminController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    FeedbackService feedbackService;

    Integer page = 0;

    @RequestMapping(value = {"/","/home","trangchu","management",""})
    public String homeAdminPage(Model model){
        List<Product> listProduct = productService.findTop6ProductByCategoryId(1);
        List<Customer> customerList = customerService.findAllCustomer();
        model.addAttribute("listProduct",listProduct);
        model.addAttribute("listUser",customerList);
        model.addAttribute("page",page);
        return "admin_page/admin_home_page";
    }

}
