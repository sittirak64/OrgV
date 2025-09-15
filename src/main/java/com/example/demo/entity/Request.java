package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long id;

    // ความสัมพันธ์กับร้านค้า
    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shops shop;

    @Column(name = "req_vege_name", nullable = false)
    private String vegeName;

    @Column(name = "req_shoplocation")
    private String shopLocation;

    @Column(name = "req_dateinspection")
    private LocalDate dateInspection;

    @Column(name = "req_appointmentday", nullable = true)
    private LocalDate appointmentDay;

    @Column(name = "req_status", columnDefinition = "ENUM('pending','approved','rejected') DEFAULT 'pending'")
    private String status = "pending";

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RequestItem> items;
    // ---------------------------
    // Getter & Setter
    // ---------------------------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Shops getShop() { return shop; }
    public void setShop(Shops shop) { this.shop = shop; }

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

    public List<RequestItem> getItems() { return items; }
    public void setItems(List<RequestItem> items) {
        this.items = items;
        if (items != null) {
            for (RequestItem item : items) {
                item.setRequest(this); // link item กับ request
            }
        }
    }
}
