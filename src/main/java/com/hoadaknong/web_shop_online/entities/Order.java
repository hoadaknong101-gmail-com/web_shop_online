package com.hoadaknong.web_shop_online.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;
    private Date orderDate;
    private String status;
    private String comment;
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    private Date modifiedDate;

    public Order() {
    }

    public Order(Integer id, Integer customerId, Date orderDate, String status, String comment, Double totalPrice, Address deliveryAddress, Date modifiedDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.comment = comment;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", totalPrice=" + totalPrice +
                ", deliveryAddress=" + deliveryAddress +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


}
