package com.example.demo.service;

import com.example.demo.entity.Request;
import com.example.demo.entity.VegeRequest;
import com.example.demo.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    // ---------------------------
    // สร้าง Request พร้อม VegeRequest หลายตัว
    // ---------------------------
    @Transactional
    public Request createRequest(Request request) {
        if (request.getVegeList() != null) {
            for (VegeRequest vege : request.getVegeList()) {
                vege.setRequest(request); // ตั้งความสัมพันธ์
            }
        }
        return repository.save(request);
    }

    // ---------------------------
    // ดึง Request ตาม shopId
    // ---------------------------
    public List<Request> getRequestsByShop(Long shopId) {
        return repository.findByShopId(shopId);
    }

    // ---------------------------
    // ดึง Request ตาม status
    // ---------------------------
    public List<Request> getRequestsByStatus(String status) {
        return repository.findByStatus(status);
    }

    // ---------------------------
    // อัพเดต status ของ Request
    // ---------------------------
    @Transactional
    public Request updateStatus(Long requestId, String status) {
        Request request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));
        request.setStatus(status);
        return repository.save(request);
    }

    // ---------------------------
    // ดึง Request ทั้งหมด
    // ---------------------------
    public List<Request> getAllRequests() {
        return repository.findAll();
    }

}
