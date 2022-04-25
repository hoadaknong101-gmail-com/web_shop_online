package com.hoadaknong.web_shop_online.services.impl;

import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;
import com.hoadaknong.web_shop_online.repositories.OrderDetailsRepository;
import com.hoadaknong.web_shop_online.repositories.OrderRepository;
import com.hoadaknong.web_shop_online.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderImplement implements OrderService {

    @Autowired
    private OrderRepository rpOrder;

    @Autowired
    private OrderDetailsRepository rpOrderDetails;

    @Override
    public List<Order> findAllOrderProccessing() {
        return rpOrder.findAllByStatus(0);
    }

    @Override
    public List<Order> findAllOrderDelivering() {
        return rpOrder.findAllByStatus(1);
    }

    @Override
    public List<Order> findAllOrderDeliveried() {
        return rpOrder.findAllByStatus(2);
    }

    @Override
    public List<Order> findAll() {
        return rpOrder.findAll();
    }

    @Override
    public Order getById(Integer id) {
        return rpOrder.getById(id);
    }

    @Override
    public void saveOrder(Order order) {
        rpOrder.save(order);
    }

    @Override
    public void deleteOrder(Integer id) {
        rpOrder.deleteById(id);
    }

    @Override
    public List<Order> findByCustomerId(Integer id) {
        return rpOrder.findAllByCustomerId(id);
    }

    @Override
    public void saveItem(OrderDetails orderDetails) {
        rpOrderDetails.save(orderDetails);
    }

    @Override
    public void deleteItem(OrderDetailsKey id) {
        rpOrderDetails.deleteById(id);
    }

    @Override
    public List<OrderDetails> findByOrderId(Integer id) {
        Order o = rpOrder.getById(id);
        return rpOrderDetails.findAllByOrderId(o);
    }


    @Override
    public List<Order> findAllOrderByOrderDateBetween(Date start, Date end) {
        return rpOrder.findAllByOrderDateBetween(start, end);
    }

    @Override
    public List<Order> findAllOrderByOrderDate(Date date) {
        return rpOrder.findAllByOrderDate(date);
    }
}
