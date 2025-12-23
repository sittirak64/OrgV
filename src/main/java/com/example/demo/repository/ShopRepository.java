package com.example.demo.repository;

import com.example.demo.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shops, Long> {

    Optional<Shops> findByUsername(String username);

    // ✅ เช็กว่ามีร้านอื่นที่ใช้พิกัดเดียวกันหรือไม่ (กันหมุดซ้ำ)
    @Query(value = """
        SELECT 1
        FROM shops s
        WHERE s.shop_id <> :shopId
          AND ROUND(s.latitude, 6) = ROUND(:lat, 6)
          AND ROUND(s.longitude, 6) = ROUND(:lng, 6)
        LIMIT 1
        """, nativeQuery = true)
    Integer existsSameLatLngExcept(@Param("shopId") Long shopId,
                                   @Param("lat") double lat,
                                   @Param("lng") double lng);
}
