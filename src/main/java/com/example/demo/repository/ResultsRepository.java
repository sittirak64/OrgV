package com.example.demo.repository;

import com.example.demo.entity.Results;
import com.example.demo.dto.ResultsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ResultsRepository extends JpaRepository<Results, Long> {
    List<Results> findByShopId(Long shopId);
    List<Results> findByVegeName(String vegeName);

    @Query("SELECT new com.example.demo.dto.ResultsDTO(r.shop.id, r.shopName, r.vegeName, r.result, r.location) FROM Results r")
    List<ResultsDTO> findShopnameAndLocation();
}
