package com.example.tinyshop.model.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddNewProductDTO {

    @Size(min = 4, max = 25)
    @NotBlank
    private String name;

    @Size(min = 4, max = 20)
    @NotBlank
    private String brand;

    @Size(min = 2, max = 20)
    @NotBlank
    private String color;

    @Size(min = 1, max = 15)
    @NotBlank
    private String size;

    @Positive
    @NotNull
    private BigDecimal price;

    @Size(min = 2, max = 20)
    @NotBlank
    private String fabric;

    @Size(min = 10, max = 500)
    @NotBlank
    private String path;

    @Positive
    @NotNull
    private long category;

    @Positive
    @NotNull
    private long collection;

    public AddNewProductDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public long getCollection() {
        return collection;
    }

    public void setCollection(long collection) {
        this.collection = collection;
    }
}
