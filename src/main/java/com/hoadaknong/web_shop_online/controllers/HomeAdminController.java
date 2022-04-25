package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.FeedbackService;
import com.hoadaknong.web_shop_online.services.OrderService;
import com.hoadaknong.web_shop_online.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = {"/","/home","trangchu","management",""})
    public String homeAdminPage(){


        return "admin_home_page";
    }

}
