package com.example.demo.controller;

import com.example.demo.entity.Request;
import com.example.demo.service.RequestService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.RequestsDTO;
import com.example.demo.dto.GroupedRequestDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    // 📌 สร้างคำขอใหม่
    @PostMapping("/create")
    public RequestsDTO createRequest(@RequestBody Request request) {
        return service.createRequest(request);
    }

    // 📌 ดึงคำขอทั้งหมด
    @GetMapping("/all")
    public Map<LocalDate, List<RequestsDTO>> getAllRequestsGrouped() {
        return service.getAllGroupedByDate();
    }

    // 📌 ดึงคำขอตาม shopId
    @GetMapping("/shop/{shopId}")
    public List<Request> getRequestsByShop(@PathVariable Long shopId) {
        return service.getRequestsByShop(shopId);
    }

    // 📌 ดึงคำขอตามสถานะ
    @GetMapping("/status/{status}")
    public List<Request> getRequestsByStatus(@PathVariable String status) {
        return service.getRequestsByStatus(status);
    }

    // 📌 อัปเดตสถานะคำขอ (pending → approved/rejected)
    @PutMapping("/{id}/status")
    public Request updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }
    // 📌 อัปเดตวันนัดตรวจสอบ (appointmentDay)
    @PutMapping("/appointment-group")
    public String updateAppointmentDayForGroup(@RequestBody Map<String, String> body) {

        Long shopId = Long.parseLong(body.get("shopId"));
        LocalDate dateInspection = LocalDate.parse(body.get("dateInspection"));
        LocalDate appointmentDay = LocalDate.parse(body.get("appointmentDay"));

        service.updateAppointmentDayForGroup(shopId, dateInspection, appointmentDay);

        return "Appointment updated for all requests in this group";
    }

    @PutMapping("/{id}/appointment")
    public Request updateAppointmentDay(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        LocalDate appointmentDay = LocalDate.parse(body.get("appointmentDay"));
        return service.updateAppointmentDay(id, appointmentDay);
    }

    @GetMapping("/grouped-by-date/{shopId}")
    public List<GroupedRequestDTO> getGroupedByDateByShop(@PathVariable Long shopId) {
        return service.getRequestsGroupedByDateInspectionByShop(shopId);
    }
}
