package com.hoadaknong.web_shop_online.services.impl;

import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;
import com.hoadaknong.web_shop_online.repositories.OrderDetailsRepository;
import com.hoadaknong.web_shop_online.repositories.OrderRepository;
import com.hoadaknong.web_shop_online.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderImplement implements OrderService {

    @Autowired
    private OrderRepository rpOrder;

    @Autowired
    private OrderDetailsRepository rpOrderDetails;

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
        return null;
    }
}
