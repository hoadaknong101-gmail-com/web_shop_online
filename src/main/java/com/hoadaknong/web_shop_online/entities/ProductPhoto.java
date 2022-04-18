package com.hoadaknong.web_shop_online.entities;

import javax.persistence.*;

@Entity
@Table(name = "tbl_product_photo")
public class ProductPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String note;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    public ProductPhoto() {
    }

    public ProductPhoto(Integer id, String note, String photo, Product productId) {
        this.id = id;
        this.note = note;
        this.photo = photo;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductPhoto{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", photo='" + photo + '\'' +
                ", productId=" + productId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
}
