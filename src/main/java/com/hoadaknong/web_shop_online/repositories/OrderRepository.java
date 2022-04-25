package com.hoadaknong.web_shop_online.repositories;

import com.hoadaknong.web_shop_online.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomerId(Integer id);
    List<Order> findAllByStatus(Integer id);
    Optional<Order> findByModifiedDate(Date date);
    List<Order> findAllByOrderDate(Date date);
    List<Order> findAllByOrderDateBetween(Date start, Date end);
}
