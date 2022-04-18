package com.hoadaknong.web_shop_online.entities;

import com.hoadaknong.web_shop_online.entities.keys.FeedbackKey;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customerId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product productId;

    private String content;
    private Integer rate;
    private Date modifiedDate;

    public Feedback(){

    }

    public Feedback(Customer customerId, Product productId, String content, Integer rate, Date modifiedDate) {
        this.customerId = customerId;
        this.productId = productId;
        this.content = content;
        this.rate = rate;
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                ", content='" + content + '\'' +
                ", rate=" + rate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
