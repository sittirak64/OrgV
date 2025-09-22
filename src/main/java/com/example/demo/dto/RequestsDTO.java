package com.example.demo.dto;

import java.time.LocalDate;

public class RequestsDTO {
    private String shopLocation;
    private LocalDate dateInspection;
    private LocalDate appointmentDay;
    private String status;

    // constructor
    public RequestsDTO(String shopLocation, LocalDate dateInspection, LocalDate appointmentDay, String status) {
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
}
