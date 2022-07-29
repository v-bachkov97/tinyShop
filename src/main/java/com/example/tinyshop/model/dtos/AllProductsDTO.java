package com.example.tinyshop.model.dtos;

import java.math.BigDecimal;

public class AllProductsDTO {

    private long id;
    private String name;
    private BigDecimal price;
    private String path;

    public AllProductsDTO(){

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

