package com.hoadaknong.web_shop_online.services;



import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);
    void deleteOrder(Integer id);
    List<Order> findByCustomerId(Integer id);

    void saveItem(OrderDetails orderDetails);
    void deleteItem(OrderDetailsKey id);
    List<OrderDetails> findByOrderId(Integer id);
}
