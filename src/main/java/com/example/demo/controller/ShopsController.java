package com.example.demo.controller;

import com.example.demo.entity.Shops;
import com.example.demo.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.ShopsDTO;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/shops")
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

    // ✅ ล็อกอิน -> return JSON { success: true, shop_id: xxx }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Shops shop) {
        Shops foundShop = shopService.login(shop.getUsername(), shop.getPassword());

        if (foundShop != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("shop_id", foundShop.getId());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // แก้ไขข้อมูลร้าน
    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> updateShop(
            @PathVariable Long id,
            @RequestBody Shops shop) {

        Map<String, Object> response = new HashMap<>();
        try {
            shopService.updateShop(id, shop);  // service จะตรวจสอบ oldPassword ด้วยถ้าเปลี่ยน password

            response.put("success", true);
            response.put("message", "Updated successfully");
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ✅ ดึงข้อมูลร้านค้าโดย shop_id (ไม่ส่ง username, password)
    @GetMapping("/{shopId}")
    public ResponseEntity<?> getShopById(@PathVariable Long shopId) {
        Shops shop = shopService.getShopById(shopId);

        if (shop != null) {
            ShopsDTO dto = new ShopsDTO(
                    shop.getId(),
                    shop.getShopName(),
                    shop.getOwnerFname(),
                    shop.getOwnerLname(),
                    shop.getHouseNumber(),
                    shop.getMoo(),
                    shop.getStreet(),
                    shop.getTumbon(),
                    shop.getAmper(),
                    shop.getProvince(),
                    shop.getPhone()
            );
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
        }
    }
}
