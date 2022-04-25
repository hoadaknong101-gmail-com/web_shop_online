package com.hoadaknong.web_shop_online.controllers;

import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping(value="/web_shop/admin/orders")
    public String orderAdminPage(Model model){
        Integer status = 0;
        List<Order> orderList = orderService.findAllOrderProccessing();
        Integer amount = orderList.size();
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);

        return "admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders_delivering")
    public String orderDeliveringAdminPage(Model model){
        Integer status = 1;
        List<Order> orderList = orderService.findAllOrderDelivering();
        Integer amount = orderList.size();
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);


        return "admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders_deliveried")
    public String orderDeliveriedAdminPage(Model model){
        Integer status = 2;
        List<Order> orderList = orderService.findAllOrderDeliveried();
        Integer amount = orderList.size();
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);

        return "admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders/details/{id}")
    public String orderForm(@PathVariable Integer id, Model model){
        Order order = orderService.getById(id);
        List<OrderDetails> listDetails = orderService.findByOrderId(id);
        model.addAttribute("order",order);
        model.addAttribute("listDetails",listDetails);

        return "admin_order_form";
    }
}
