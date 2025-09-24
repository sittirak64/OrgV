package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class GroupedRequestDTO {
    private LocalDate dateInspection;
    private List<RequestsDTO> requests;

    public GroupedRequestDTO(LocalDate dateInspection, List<RequestsDTO> requests) {
        this.dateInspection = dateInspection;
        this.requests = requests;
    }

    public LocalDate getDateInspection() { return dateInspection; }
    public void setDateInspection(LocalDate dateInspection) { this.dateInspection = dateInspection; }

    public List<RequestsDTO> getRequests() { return requests; }
    public void setRequests(List<RequestsDTO> requests) { this.requests = requests; }
}
