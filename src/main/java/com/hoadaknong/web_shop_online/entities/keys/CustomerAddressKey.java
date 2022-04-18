package com.hoadaknong.web_shop_online.entities.keys;

import java.io.Serializable;

public class CustomerAddressKey implements Serializable {
    private Integer customerId;
    private Integer addressId;

    public CustomerAddressKey() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public CustomerAddressKey(Integer customerId, Integer addressId) {
        this.customerId = customerId;
        this.addressId = addressId;
    }
}
