package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.entities.keys.OrderDetailsKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsKey> {
    List<OrderDetails> findAllByOrderId(Order orderId);
}
