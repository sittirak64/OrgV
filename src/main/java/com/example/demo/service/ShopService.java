package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.demo.entity.Shops;
import com.example.demo.repository.ShopRepository;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    // Register
    public String register(Shops shop) {
        if (shopRepository.findByUsername(shop.getUsername()).isPresent()) {
            return "Username already exists";
        }
        // TODO: hash password ก่อนบันทึกจริง
        shopRepository.save(shop);
        return "Register success";
    }

    // ✅ Login -> return Shops แทน boolean
    public Shops login(String username, String password) {
        Optional<Shops> shop = shopRepository.findByUsername(username);
        if (shop.isPresent() && shop.get().getPassword().equals(password)) {
            return shop.get(); // ส่งกลับทั้ง object
        }
        return null; // ถ้า login ไม่สำเร็จ
    }

    // Update shop
    public Shops updateShop(Long shopId, Shops updatedShop) {
        Shops existingShop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));

        if (updatedShop.getOwnerFname() != null) existingShop.setOwnerFname(updatedShop.getOwnerFname());
        if (updatedShop.getOwnerLname() != null) existingShop.setOwnerLname(updatedShop.getOwnerLname());
        if (updatedShop.getShopName() != null) existingShop.setShopName(updatedShop.getShopName());
        if (updatedShop.getHouseNumber() != null) existingShop.setHouseNumber(updatedShop.getHouseNumber());
        if (updatedShop.getMoo() != null) existingShop.setMoo(updatedShop.getMoo());
        if (updatedShop.getStreet() != null) existingShop.setStreet(updatedShop.getStreet());
        if (updatedShop.getTumbon() != null) existingShop.setTumbon(updatedShop.getTumbon());
        if (updatedShop.getAmper() != null) existingShop.setAmper(updatedShop.getAmper());
        if (updatedShop.getProvince() != null) existingShop.setProvince(updatedShop.getProvince());
        if (updatedShop.getPhone() != null) existingShop.setPhone(updatedShop.getPhone());
        if (updatedShop.getUsername() != null) existingShop.setUsername(updatedShop.getUsername());
        if (updatedShop.getPassword() != null) existingShop.setPassword(updatedShop.getPassword());

        return shopRepository.save(existingShop);
    }
}
