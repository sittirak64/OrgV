package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shops shop;

    @Column(name = "req_shoplocation")
    private String shopLocation;

    @Column(name = "req_dateinspection")
    private LocalDate dateInspection;

    @Column(name = "req_appointmentday", nullable = true)
    private LocalDate appointmentDay;

    @Column(name = "req_status", columnDefinition = "ENUM('pending','approved','rejected') DEFAULT 'pending'")
    private String status = "pending";

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VegeRequest> vegeList = new ArrayList<>();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shops getShop() { return shop; }
    public void setShop(Shops shop) { this.shop = shop; }

    public String getShopLocation() { return shopLocation; }
    public void setShopLocation(String shopLocation) { this.shopLocation = shopLocation; }

    public LocalDate getDateInspection() { return dateInspection; }
    public void setDateInspection(LocalDate dateInspection) { this.dateInspection = dateInspection; }

    public LocalDate getAppointmentDay() { return appointmentDay; }
    public void setAppointmentDay(LocalDate appointmentDay) { this.appointmentDay = appointmentDay; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<VegeRequest> getVegeList() { return vegeList; }
    public void setVegeList(List<VegeRequest> vegeList) { this.vegeList = vegeList; }
}
