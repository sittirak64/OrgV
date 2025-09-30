package com.example.demo.repository;

import com.example.demo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByShopId(Long shopId);

    List<Request> findByStatus(String status);

    // สำหรับอัปเดต appointmentDay ทั้งกลุ่ม
    List<Request> findByShopIdAndDateInspection(Long shopId, LocalDate dateInspection);
    List<Request> findByShopIdAndAppointmentDay(Long shopId, LocalDate appointmentDay);

}
