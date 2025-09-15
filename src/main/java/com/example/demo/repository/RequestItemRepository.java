package com.example.demo.repository;

import com.example.demo.entity.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {

    // ดึง RequestItem ทั้งหมดของ Request หนึ่ง ๆ
    List<RequestItem> findByRequestId(Long requestId);

    // ถ้าอยาก filter ตามชื่อผัก
    List<RequestItem> findByVegeName(String vegeName);
}
