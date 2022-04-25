package com.hoadaknong.web_shop_online.services;



import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAllOrderProccessing();
    List<Order> findAllOrderDelivering();
    List<Order> findAllOrderDeliveried();
    List<Order> findAll();
    Order getById(Integer id);
    void saveOrder(Order order);
    void deleteOrder(Integer id);
    List<Order> findByCustomerId(Integer id);

    void saveItem(OrderDetails orderDetails);
    void deleteItem(OrderDetailsKey id);
    List<OrderDetails> findByOrderId(Integer id);

    List<Order> findAllOrderByOrderDateBetween(Date start, Date end);
    List<Order> findAllOrderByOrderDate(Date date);
}
