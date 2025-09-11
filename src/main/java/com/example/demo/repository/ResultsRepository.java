package com.example.demo.repository;

import com.example.demo.entity.Results;
import com.example.demo.dto.ResultsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ResultsRepository extends JpaRepository<Results, Long> {

    // ดึงผลตรวจทั้งหมดของร้าน (ตาม shopId)
    List<Results> findByShopId(Long shopId);

    // ดึงผลตรวจทั้งหมดตามชื่อผัก (resuVegeName)
    List<Results> findByResuVegeName(String resuVegeName);

    // ดึงเฉพาะ shopname + location
    @Query("SELECT new com.example.demo.dto.ResultsDTO(r.resuShopname, r.resuLocation) FROM Results r")
    List<ResultsDTO> findShopnameAndLocation();
}
