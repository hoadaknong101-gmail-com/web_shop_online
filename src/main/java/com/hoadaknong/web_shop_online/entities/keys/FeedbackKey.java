package com.hoadaknong.web_shop_online.entities.keys;

import java.io.Serializable;

public class FeedbackKey implements Serializable {
    private Integer customerId;
    private Integer productId;


    public FeedbackKey() {
    }

    public FeedbackKey(Integer customerId, Integer productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
