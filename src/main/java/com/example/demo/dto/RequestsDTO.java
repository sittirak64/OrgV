package com.example.demo.dto;

import java.time.LocalDate;

public class RequestsDTO {
    private String shopLocation;
    private String vegeName;
    private LocalDate dateInspection;
    private LocalDate appointmentDay;
    private String status;

    public RequestsDTO(String shopLocation,
                       String vegeName,
                       LocalDate dateInspection,
                       LocalDate appointmentDay,
                       String status) {
        this.vegeName = vegeName;
        this.shopLocation = shopLocation;
        this.dateInspection = dateInspection;
        this.appointmentDay = appointmentDay;
        this.status = status;
    }

    // getters
    public String getShopLocation() { return shopLocation; }
    public LocalDate getDateInspection() { return dateInspection; }
    public LocalDate getAppointmentDay() { return appointmentDay; }
    public String getStatus() { return status; }
    public String getVegeName() { return vegeName; }
}
