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
import javax.servlet.http.HttpSession;
import java.util.*;

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
    public String viewCustomerCart(@PathVariable Integer customerId,
                                   Model model,
                                   HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        Customer customer = customerService.getCustomerById(customerId);
        Optional<Order> orderFound = orderService.findOrderByCustomerAndStatus(customer, CART_STATUS);
        Order order = null;
        if(userId != customerId){
            return "redirect:/sign_in_sign_up";
        }
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

    @RequestMapping(value="/order_list/{customerId}")
    public String orderListPage(@PathVariable Integer customerId,
                                HttpServletRequest request,
                                Model model){
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId != customerId){
            return "client_page/error_checkout";
        }
        Customer customer = customerService.getCustomerById(customerId);
        List<Order> listOrder = orderService.findByCustomerId(customer);
        Collections.reverse(listOrder);
        model.addAttribute("listOrder", listOrder);

        return "client_page/order";
    }
    @RequestMapping(value="/order_details/{orderId}")
    public String orderDetailPage(@PathVariable Integer orderId,
                                  Model model){
        Order order = orderService.getById(orderId);
        List<OrderDetails> listOrderDetail = orderService.findByOrderId(order.getId());
        model.addAttribute("listOrderDetail", listOrderDetail);
        model.addAttribute("order", order);

        return "client_page/order_details";
    }

    @RequestMapping(value="/cancel/{id}")
    public String cancelOrder(@PathVariable Integer id,
                              HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("userId");
        Customer customer = null;
        if(customerId != null){
            customer = customerService.getCustomerById(customerId);
            if(customer != null){
                Order order = orderService.getById(id);
                order.setStatus(3);
                orderService.saveOrder(order);

                return "redirect:/cart/order_list/" + customerId;
            }
        }
        return "error/error_404";
    }
}
