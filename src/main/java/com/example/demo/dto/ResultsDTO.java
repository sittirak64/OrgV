package com.example.demo.dto;

public class ResultsDTO {
    private String shopName;
    private String location;

    public ResultsDTO(String shopName, String location) {
        this.shopName = shopName;
        this.location = location;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
