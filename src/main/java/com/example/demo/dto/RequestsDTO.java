package com.example.demo.dto;

import java.time.LocalDate;

public class RequestsDTO {
    private Long id;
    private ShopsDTO shop;   // 🔹 เปลี่ยนมาใช้ ShopsDTO
    private String vegeName;
    private String shopLocation;
    private LocalDate dateInspection;
    private LocalDate appointmentDay;
    private String status;

    public RequestsDTO(Long id, ShopsDTO shop, String vegeName,
                       String shopLocation, LocalDate dateInspection,
                       LocalDate appointmentDay, String status) {
        this.id = id;
        this.shop = shop;
        this.vegeName = vegeName;
        this.shopLocation = shopLocation;
        this.dateInspection = dateInspection;
        this.appointmentDay = appointmentDay;
        this.status = status;
    }

    // ✅ Getter & Setter
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
}
