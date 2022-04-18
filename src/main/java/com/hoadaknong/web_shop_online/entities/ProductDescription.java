package com.hoadaknong.web_shop_online.entities;

import javax.persistence.*;

@Entity
@Table(name ="tbl_product_description")
public class ProductDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    public ProductDescription() {
    }

    public ProductDescription(Integer id, String title, String content, Product productId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductDescription{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", productId=" + productId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
}
