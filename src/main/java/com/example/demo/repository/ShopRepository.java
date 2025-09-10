package com.example.demo.repository;

import com.example.demo.entity.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shops, Long> {
    Optional<Shops> findByUsername(String username);
}
