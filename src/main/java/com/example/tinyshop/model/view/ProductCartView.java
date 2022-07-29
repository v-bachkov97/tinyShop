package com.example.tinyshop.model.view;

import java.math.BigDecimal;

public class ProductCartView {
    private long id;
    private String name;
    private String path;
    private BigDecimal price;

    public ProductCartView(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
