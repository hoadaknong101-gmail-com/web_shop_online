package com.hoadaknong.web_shop_online.entities.keys;

import java.io.Serializable;

public class OrderDetailsKey implements Serializable {
    private Integer productId;
    private Integer orderId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderDetailsKey(Integer productId, Integer orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public OrderDetailsKey(){}
}
