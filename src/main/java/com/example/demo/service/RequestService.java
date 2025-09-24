package com.example.demo.service;

import com.example.demo.entity.Request;
import com.example.demo.repository.RequestRepository;
import com.example.demo.dto.RequestsDTO;
import com.example.demo.dto.GroupedRequestDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public List<Request> getRequestsByShop(Long shopId) {
        return repository.findByShopId(shopId);
    }

    public List<Request> getRequestsByStatus(String status) {
        return repository.findByStatus(status);
    }

    public RequestsDTO createRequest(Request request) {
        Request saved = repository.save(request);
        return new RequestsDTO(
                saved.getShopLocation(),
                saved.getVegeName(),
                saved.getDateInspection(),
                saved.getAppointmentDay(),
                saved.getStatus()
        );
    }

    @Transactional
    public Request updateStatus(Long requestId, String status) {
        Request request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));
        request.setStatus(status);
        return repository.save(request);
    }

    public List<Request> getAllRequests() {
        return repository.findAll();
    }

    @Transactional
    public Request updateAppointmentDay(Long requestId, LocalDate appointmentDay) {
        Request request = repository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found with id: " + requestId));
        request.setAppointmentDay(appointmentDay);
        return repository.save(request);
    }

    @Transactional
    public void updateAppointmentDayForGroup(Long shopId, LocalDate dateInspection, LocalDate appointmentDay) {
        List<Request> requests = repository.findByShopIdAndDateInspection(shopId, dateInspection);
        for (Request req : requests) {
            req.setAppointmentDay(appointmentDay);
        }
        repository.saveAll(requests);
    }

    public List<GroupedRequestDTO> getRequestsGroupedByDateInspectionByShop(Long shopId) {
        List<Request> requests = repository.findByShopId(shopId);

        Map<LocalDate, List<RequestsDTO>> grouped = requests.stream()
                .map(req -> new RequestsDTO(
                        req.getShopLocation(),
                        req.getVegeName(),
                        req.getDateInspection(),
                        req.getAppointmentDay(),
                        req.getStatus()
                ))
                .collect(Collectors.groupingBy(RequestsDTO::getDateInspection));

        return grouped.entrySet().stream()
                .map(e -> new GroupedRequestDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
