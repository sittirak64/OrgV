package com.example.demo.dto;

public class ResultsDTO {
    private String resuShopname;
    private String resuLocation;

    public ResultsDTO(String resuShopname, String resuLocation) {
        this.resuShopname = resuShopname;
        this.resuLocation = resuLocation;
    }

    public String getResuShopname() {
        return resuShopname;
    }

    public void setResuShopname(String resuShopname) {
        this.resuShopname = resuShopname;
    }

    public String getResuLocation() {
        return resuLocation;
    }

    public void setResuLocation(String resuLocation) {
        this.resuLocation = resuLocation;
    }
}
