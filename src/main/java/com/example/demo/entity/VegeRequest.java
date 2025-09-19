package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vege_requests")
public class VegeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @Column(name = "vege_name", nullable = false)
    private String vegeName;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Request getRequest() { return request; }
    public void setRequest(Request request) { this.request = request; }

    public String getVegeName() { return vegeName; }
    public void setVegeName(String vegeName) { this.vegeName = vegeName; }
}
