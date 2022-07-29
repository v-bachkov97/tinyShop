package com.example.tinyshop.model.view;

import java.math.BigDecimal;

public class LatestProductsView {
    private long id;
    private String path;
    private String name;
    private BigDecimal price;

    public LatestProductsView(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
