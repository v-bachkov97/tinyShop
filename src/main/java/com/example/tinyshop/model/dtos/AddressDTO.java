package com.example.tinyshop.model.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AddressDTO {

    @Size(min = 2,max = 20)
    private String cityOrProvince;

    @Size(min = 2,max = 35)
    private String name;


    @Positive
    private int number;


    @Positive
    private int postalCode;

    public AddressDTO(){

    }

    public String getCityOrProvince() {
        return cityOrProvince;
    }

    public void setCityOrProvince(String cityOrProvince) {
        this.cityOrProvince = cityOrProvince;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
