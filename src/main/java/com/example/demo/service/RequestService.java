package com.example.demo.service;

import com.example.demo.entity.Request;
import com.example.demo.repository.RequestRepository;
import com.example.demo.dto.RequestsDTO;
import com.example.demo.dto.GroupedRequestDTO;
import com.example.demo.dto.ShopsDTO;
import com.example.demo.dto.AllRequestsGroupedDTO;

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

        ShopsDTO shopDTO = new ShopsDTO(
                saved.getShop().getId(),
                saved.getShop().getShopName(),
                saved.getShop().getOwnerFname(),
                saved.getShop().getOwnerLname(),
                saved.getShop().getHouseNumber(),
                saved.getShop().getMoo(),
                saved.getShop().getStreet(),
                saved.getShop().getTumbon(),
                saved.getShop().getAmper(),
                saved.getShop().getProvince(),
                saved.getShop().getPhone()
        );

        return new RequestsDTO(
                saved.getId(),
                shopDTO,
                saved.getVegeName(),
                saved.getShopLocation(),
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
    @Transactional
    public void updateStatusByAppointmentDayAndShop(Long shopId, LocalDate appointmentDay, String status) {
        List<Request> requests = repository.findByShopIdAndAppointmentDay(shopId, appointmentDay);
        for (Request req : requests) {
            req.setStatus(status);
        }
        repository.saveAll(requests);
    }


    public List<GroupedRequestDTO> getRequestsGroupedByDateInspectionByShop(Long shopId) {
        List<Request> requests = repository.findByShopId(shopId);

        Map<LocalDate, List<RequestsDTO>> grouped = requests.stream()
                .map(r -> new RequestsDTO(
                        r.getId(),
                        new ShopsDTO(
                                r.getShop().getId(),
                                r.getShop().getShopName(),
                                r.getShop().getOwnerFname(),
                                r.getShop().getOwnerLname(),
                                r.getShop().getHouseNumber(),
                                r.getShop().getMoo(),
                                r.getShop().getStreet(),
                                r.getShop().getTumbon(),
                                r.getShop().getAmper(),
                                r.getShop().getProvince(),
                                r.getShop().getPhone()
                        ),
                        r.getVegeName(),
                        r.getShopLocation(),
                        r.getDateInspection(),
                        r.getAppointmentDay(),
                        r.getStatus()
                ))
                .collect(Collectors.groupingBy(RequestsDTO::getDateInspection));

        return grouped.entrySet().stream()
                .map(e -> new GroupedRequestDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<AllRequestsGroupedDTO> getAllGroupedByDate() {
        List<Request> requests = repository.findAll();

        Map<LocalDate, List<RequestsDTO>> grouped = requests.stream()
                .map(r -> new RequestsDTO(
                        r.getId(),
                        new ShopsDTO(
                                r.getShop().getId(),
                                r.getShop().getShopName(),
                                r.getShop().getOwnerFname(),
                                r.getShop().getOwnerLname(),
                                r.getShop().getHouseNumber(),
                                r.getShop().getMoo(),
                                r.getShop().getStreet(),
                                r.getShop().getTumbon(),
                                r.getShop().getAmper(),
                                r.getShop().getProvince(),
                                r.getShop().getPhone()
                        ),
                        r.getVegeName(),
                        r.getShopLocation(),
                        r.getDateInspection(),
                        r.getAppointmentDay(),
                        r.getStatus()
                ))
                .collect(Collectors.groupingBy(RequestsDTO::getDateInspection));

        return grouped.entrySet().stream()
                .map(e -> new AllRequestsGroupedDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    public List<GroupedRequestDTO> getRequestsGroupedByDate(Long shopId) {
        List<Request> requests = repository.findByShopId(shopId);

        // Group by dateInspection
        Map<LocalDate, List<RequestsDTO>> grouped = requests.stream()
                .map(r -> new RequestsDTO(
                        r.getId(),
                        new ShopsDTO(
                                r.getShop().getId(),
                                r.getShop().getShopName(),
                                r.getShop().getOwnerFname(),
                                r.getShop().getOwnerLname(),
                                r.getShop().getHouseNumber(),
                                r.getShop().getMoo(),
                                r.getShop().getStreet(),
                                r.getShop().getTumbon(),
                                r.getShop().getAmper(),
                                r.getShop().getProvince(),
                                r.getShop().getPhone()
                        ),
                        r.getVegeName(),
                        r.getShopLocation(),
                        r.getDateInspection(),
                        r.getAppointmentDay(),
                        r.getStatus()
                ))
                .collect(Collectors.groupingBy(RequestsDTO::getDateInspection));

        // แปลงเป็น List<GroupedRequestDTO>
        return grouped.entrySet().stream()
                .map(entry -> new GroupedRequestDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
    public GroupedRequestDTO getRequestsGroupedByDateInspectionByShopAndDate(Long shopId, LocalDate dateInspection) {
        List<Request> requests = repository.findByShopIdAndDateInspection(shopId, dateInspection);

        List<RequestsDTO> dtoList = requests.stream()
                .map(r -> new RequestsDTO(
                        r.getId(),
                        new ShopsDTO(
                                r.getShop().getId(),
                                r.getShop().getShopName(),
                                r.getShop().getOwnerFname(),
                                r.getShop().getOwnerLname(),
                                r.getShop().getHouseNumber(),
                                r.getShop().getMoo(),
                                r.getShop().getStreet(),
                                r.getShop().getTumbon(),
                                r.getShop().getAmper(),
                                r.getShop().getProvince(),
                                r.getShop().getPhone()
                        ),
                        r.getVegeName(),
                        r.getShopLocation(),
                        r.getDateInspection(),
                        r.getAppointmentDay(),
                        r.getStatus()
                ))
                .collect(Collectors.toList());

        return new GroupedRequestDTO(dateInspection, dtoList);
    }

}
