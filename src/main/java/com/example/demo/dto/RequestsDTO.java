package com.example.demo.dto;

import java.time.LocalDate;

public class RequestsDTO {
    private Long id;                 // req_id
    private ShopsDTO shop;
    private String vegeName;
    private String shopLocation;
    private LocalDate dateInspection;
    private LocalDate appointmentDay;
    private String status;

    // ✅ เพิ่ม: ใช้เปิดใบรับรองด้วย resu_id
    private Long resuId;

    public RequestsDTO() {}

    public RequestsDTO(Long id,
                       ShopsDTO shop,
                       String vegeName,
                       String shopLocation,
                       LocalDate dateInspection,
                       LocalDate appointmentDay,
                       String status) {
        this.id = id;
        this.shop = shop;
        this.vegeName = vegeName;
        this.shopLocation = shopLocation;
        this.dateInspection = dateInspection;
        this.appointmentDay = appointmentDay;
        this.status = status;
    }

    public RequestsDTO(Long id,
                       ShopsDTO shop,
                       String vegeName,
                       String shopLocation,
                       LocalDate dateInspection,
                       LocalDate appointmentDay,
                       String status,
                       Long resuId) {
        this.id = id;
        this.shop = shop;
        this.vegeName = vegeName;
        this.shopLocation = shopLocation;
        this.dateInspection = dateInspection;
        this.appointmentDay = appointmentDay;
        this.status = status;
        this.resuId = resuId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ShopsDTO getShop() { return shop; }
    public void setShop(ShopsDTO shop) { this.shop = shop; }

    public String getVegeName() { return vegeName; }
    public void setVegeName(String vegeName) { this.vegeName = vegeName; }

    public String getShopLocation() { return shopLocation; }
    public void setShopLocation(String shopLocation) { this.shopLocation = shopLocation; }

    public LocalDate getDateInspection() { return dateInspection; }
    public void setDateInspection(LocalDate dateInspection) { this.dateInspection = dateInspection; }

    public LocalDate getAppointmentDay() { return appointmentDay; }
    public void setAppointmentDay(LocalDate appointmentDay) { this.appointmentDay = appointmentDay; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getResuId() { return resuId; }
    public void setResuId(Long resuId) { this.resuId = resuId; }
}
