package com.hoadaknong.web_shop_online.controllers;


import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.OrderService;
import com.hoadaknong.web_shop_online.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value={"cart","order"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    Integer CART_STATUS = -1;
    Integer PROCESSING_STATUS = 0;
    Integer DELIVERING_STATUS = 1;
    Integer DELIIVERED_STATUS = 2;
    Integer CANCELLED_STATUS = 3;

    @RequestMapping(value="/{customerId}")
    public String viewCustomerCart(@PathVariable Integer customerId, Model model){
        Customer customer = customerService.getCustomerById(customerId);
        Optional<Order> orderFound = orderService.findOrderByCustomerAndStatus(customer, CART_STATUS);
        Order order = null;

        if(orderFound.isPresent()){
            order = orderFound.get();
        } else {
            order = new Order();
            order.setStatus(CART_STATUS);
            order.setCustomerId(customer);
            order.setTotalPrice(0.0);
            order.setModifiedDate(new Date());
            orderService.saveOrder(order);
        }
        List<OrderDetails> listOrderDetail = orderService.findByOrderId(order.getId());
        model.addAttribute("listOrderDetail", listOrderDetail);
        model.addAttribute("order", order);
        return "client_page/cart";
    }

    
    
    
}
