package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.ProductService;
import com.hoadaknong.web_shop_online.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value="/web_shop/admin/statistic")
public class AdminStatisticController {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    StatsService statsService;

    Integer page = 5;

    @RequestMapping(value={"/",""}, method = RequestMethod.GET)
    public String statisticPage(Model model){
        List<Product> listTop6Product = productService.findTop6ProductByCategoryId(1);
        List<Customer> listTop5Customer = customerService.findTop5Customer();
        double profitValue = statsService.getProfitUpToNow();

        model.addAttribute("profitValue", String.format("%,.0f", profitValue));
        model.addAttribute("listProduct", listTop6Product);
        model.addAttribute("listCustomer", listTop5Customer);
        model.addAttribute("page",page);

        return "admin_page/admin_statistic";
    }
}
