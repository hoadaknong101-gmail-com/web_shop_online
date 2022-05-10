package com.hoadaknong.web_shop_online.controllers.api;

import com.hoadaknong.web_shop_online.entities.Customer;
import com.hoadaknong.web_shop_online.entities.Order;
import com.hoadaknong.web_shop_online.entities.OrderDetails;
import com.hoadaknong.web_shop_online.entities.Product;
import com.hoadaknong.web_shop_online.services.CustomerService;
import com.hoadaknong.web_shop_online.services.OrderService;
import com.hoadaknong.web_shop_online.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@RestController
public class OrderAPI {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/cart/add_product/{productId}")
    public String addProductToCart(@PathVariable Integer productId,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        Product product = productService.findProductById(productId);
        Integer customerId = (Integer) session.getAttribute("userId");
        if (customerId == null) {
            return "Bạn hãy đăng nhập trước";
        } else {
            Customer customer = customerService.getCustomerById(customerId);
            Optional<Order> orderFound = orderService.findOrderByCustomerAndStatus(customer, -1);
            if (orderFound.isPresent()) { // Đã có giỏ hàng
                Order order = orderFound.get();
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderId(order);
                orderDetails.setModifiedDate(new Date());
                orderDetails.setProductId(product);
                orderDetails.setQuantity(1);
                orderDetails.setUnitPrice(product.getListPrice());
                orderDetails.setTotal(orderDetails.getQuantity() * orderDetails.getUnitPrice());
                orderService.saveItem(orderDetails);
                return "Thêm thành công";
            } else {
                Order order = new Order();
                order.setStatus(-1);
                order.setModifiedDate(new Date());
                order.setCustomerId(customer);

                orderService.saveOrder(order);
                OrderDetails orderDetails = new OrderDetails();


                orderDetails.setOrderId(order);
                orderDetails.setModifiedDate(new Date());
                orderDetails.setProductId(product);
                orderDetails.setQuantity(1);
                orderDetails.setUnitPrice(product.getListPrice());
                orderDetails.setTotal(orderDetails.getQuantity() * orderDetails.getUnitPrice());
                orderService.saveItem(orderDetails);
                return "Thêm thành công";
            }
        }
    }

    @RequestMapping("/cart/add_product/{productId}/{quantity}")
    public String addProductToCartAndQuantity(@PathVariable Integer productId,
                                              @PathVariable Integer quantity,
                                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        Product product = productService.findProductById(productId);
        Integer customerId = (Integer) session.getAttribute("userId");
        if (customerId == null) {
            return "Bạn hãy đăng nhập trước";
        } else {
            Customer customer = customerService.getCustomerById(customerId);
            Optional<Order> orderFound = orderService.findOrderByCustomerAndStatus(customer, -1);
            if (orderFound.isPresent()) { // Đã có giỏ hàng
                Order order = orderFound.get();
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setOrderId(order);
                orderDetails.setModifiedDate(new Date());
                orderDetails.setProductId(product);
                orderDetails.setQuantity(1);
                orderDetails.setUnitPrice(product.getListPrice());
                orderDetails.setTotal(orderDetails.getQuantity() * orderDetails.getUnitPrice());
                orderService.saveItem(orderDetails);
                return "Thêm thành công";
            } else {
                Order order = new Order();
                order.setStatus(-1);
                order.setModifiedDate(new Date());
                order.setCustomerId(customer);

                orderService.saveOrder(order);
                OrderDetails orderDetails = new OrderDetails();


                orderDetails.setOrderId(order);
                orderDetails.setModifiedDate(new Date());
                orderDetails.setProductId(product);
                orderDetails.setQuantity(1);
                orderDetails.setUnitPrice(product.getListPrice());
                orderDetails.setTotal(orderDetails.getQuantity() * orderDetails.getUnitPrice());
                orderService.saveItem(orderDetails);
                return "Thêm thành công";
            }
        }
    }
}
