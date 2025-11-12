package com.example.demo.repository;

import com.example.demo.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByShopId(Long shopId);
    List<Request> findByStatus(String status);
    List<Request> findByShopIdAndDateInspection(Long shopId, LocalDate dateInspection);
    List<Request> findByShopIdAndAppointmentDay(Long shopId, LocalDate appointmentDay);

    // ✅ เช็คว่าพิกัดนี้มีในคำขอของ "ร้านอื่น" แล้วหรือยัง (ปัดทศนิยม 6 ตำแหน่ง)
    //    เงื่อนไขสถานะ: pending/approved หรือค่าว่าง (ปรับได้ตามกติกา)
    @Query(value = """
        SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END
        FROM requests r
        WHERE r.shop_id <> :shopId
          AND ROUND(CAST(SUBSTRING_INDEX(r.req_shoplocation, ',', 1) AS DECIMAL(12,6)), 6) = ROUND(:lat, 6)
          AND ROUND(CAST(SUBSTRING_INDEX(r.req_shoplocation, ',', -1) AS DECIMAL(12,6)), 6) = ROUND(:lng, 6)
          AND (r.req_status IN ('pending', 'approved') OR r.req_status IS NULL)
        """, nativeQuery = true)
    Integer existsSameLocationInRequests(@Param("shopId") Long shopId,
                                         @Param("lat") double lat,
                                         @Param("lng") double lng);
                                         
}
