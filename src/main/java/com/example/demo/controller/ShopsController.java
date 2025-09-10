package com.example.demo.controller;

import com.example.demo.entity.Shops;
import com.example.demo.service.ShopService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shops")  // เปลี่ยนให้ตรงกับ Shops
public class ShopsController {

    private final ShopService shopService;

    public ShopsController(ShopService shopService) {
        this.shopService = shopService;
    }

    // สมัครร้านใหม่
    @PostMapping("/register")
    public String register(@RequestBody Shops shop) {
        return shopService.register(shop);
    }

    // ล็อกอิน
    @PostMapping("/login")
    public String login(@RequestBody Shops shop) {
        boolean success = shopService.login(shop.getUsername(), shop.getPassword());
        return success ? "Login success" : "Login failed";
    }
}
