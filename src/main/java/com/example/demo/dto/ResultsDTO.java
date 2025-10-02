package com.example.demo.dto;

public class ResultsDTO {
    private Long shopId;     // เพิ่ม shopId
    private String shopName;
    private String vegeName;
    private String result;
    private String location;

    public ResultsDTO(Long shopId, String shopName, String vegeName, String result, String location) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.vegeName = vegeName;
        this.result = result;
        this.location = location;
    }

    // Getter & Setter
    public Long getShopId() { return shopId; }
    public void setShopId(Long shopId) { this.shopId = shopId; }

    public String getShopName() { return shopName; }
    public void setShopName(String shopName) { this.shopName = shopName; }

    public String getVegeName() { return vegeName; }
    public void setVegeName(String vegeName) { this.vegeName = vegeName; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
