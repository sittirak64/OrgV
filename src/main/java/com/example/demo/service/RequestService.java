package com.example.demo.service;

import com.example.demo.entity.Request;
import com.example.demo.repository.RequestRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public Request createRequest(Request request) {
        return repository.save(request);
    }

    public List<Request> getRequestsByShop(Long shopId) {
        return repository.findByShopId(shopId);
    }

    public List<Request> getRequestsByStatus(String status) {
        return repository.findByStatus(status);
    }

    public Request updateStatus(Long requestId, String status) {
        Request request = repository.findById(requestId).orElseThrow();
        request.setStatus(status);
        return repository.save(request);
    }
    public List<Request> getAllRequests() {
        return repository.findAll();
    }

}
