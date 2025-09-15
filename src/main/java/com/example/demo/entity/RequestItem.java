package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "request_items")
public class RequestItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ความสัมพันธ์กับ Request
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @Column(name = "vege_name", nullable = false)
    private String vegeName;

    // ------------------- Getter & Setter -------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }

    public String getVegeName() { return vegeName; }
    public void setVegeName(String vegeName) { this.vegeName = vegeName; }
}
