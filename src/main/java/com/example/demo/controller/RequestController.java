package com.example.demo.controller;

import com.example.demo.entity.Request;
import com.example.demo.service.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService service;

    public RequestController(RequestService service) {
        this.service = service;
    }

    // 📌 สร้างคำขอใหม่
    @PostMapping("/create")
    public Request createRequest(@RequestBody Request request) {
        return service.createRequest(request);
    }

    // 📌 ดึงคำขอทั้งหมด
    @GetMapping("/all")
    public List<Request> getAllRequests() {
        return service.getAllRequests();  // ต้องไปเพิ่ม method นี้ใน service
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
}
