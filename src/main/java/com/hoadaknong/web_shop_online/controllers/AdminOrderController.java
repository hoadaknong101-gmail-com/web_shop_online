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
public class AdminOrderController {
    @Autowired
    OrderService orderService;

    Integer CART_STATUS = -1;
    Integer PROCESSING_STATUS = 0;
    Integer DELIVERING_STATUS = 1;
    Integer DELIIVERED_STATUS = 2;
    Integer CANCELLED_STATUS = 3;

    Integer page = 3;

    @RequestMapping(value="/web_shop/admin/orders")
    public String orderAdminPage(Model model){
        Integer status = 0;
        List<Order> orderList = orderService.findAllOrderProccessing();
        Integer amount = orderList.size();

        model.addAttribute("page",page);
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);

        return "admin_page/admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders_delivering")
    public String orderDeliveringAdminPage(Model model){
        Integer status = 1;
        List<Order> orderList = orderService.findAllOrderDelivering();
        Integer amount = orderList.size();
        model.addAttribute("page",page);
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);


        return "admin_page/admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders_deliveried")
    public String orderDeliveriedAdminPage(Model model){
        Integer status = 2;
        List<Order> orderList = orderService.findAllOrderDeliveried();
        Integer amount = orderList.size();
        model.addAttribute("page",page);
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);

        return "admin_page/admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders_cancelled")
    public String orderCancelledAdminPage(Model model){
        Integer status = CANCELLED_STATUS;
        List<Order> orderList = orderService.findAllOrderCancelled();
        Integer amount = orderList.size();
        model.addAttribute("page",page);
        model.addAttribute("listOrder",orderList);
        model.addAttribute("amount",amount);
        model.addAttribute("status",status);

        return "admin_page/admin_order";
    }

    @RequestMapping(value="/web_shop/admin/orders/details/{id}")
    public String orderForm(@PathVariable Integer id, Model model){
        Order order = orderService.getById(id);
        List<OrderDetails> listDetails = orderService.findByOrderId(id);
        model.addAttribute("page",page);
        model.addAttribute("order",order);
        model.addAttribute("listDetails",listDetails);
        model.addAttribute("status",order.getStatus());

        return "admin_page/admin_order_form";
    }

    @RequestMapping(value = "/web_shop/admin/orders/delivering/{orderId}")
    public String deliveringOrder(@PathVariable Integer orderId){
        Order order = orderService.getById(orderId);
        order.setStatus(DELIVERING_STATUS);
        orderService.saveOrder(order);

        return "redirect:/web_shop/admin/orders_delivering";
    }
    @RequestMapping(value = "/web_shop/admin/orders/delivered/{orderId}")
    public String deliveredOrder(@PathVariable Integer orderId){
        Order order = orderService.getById(orderId);
        order.setStatus(DELIIVERED_STATUS);
        orderService.saveOrder(order);

        return "redirect:/web_shop/admin/orders_deliveried";
    }

    @RequestMapping(value ="/web_shop/admin/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable Integer orderId){
        Order order = orderService.getById(orderId);
        order.setStatus(CANCELLED_STATUS);
        orderService.saveOrder(order);

        return "redirect:/web_shop/admin/orders";
    }
}
