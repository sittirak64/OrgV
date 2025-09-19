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
    public Shops updateShop(Long shopId, Shops shop) {
        Shops existingShop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found with id: " + shopId));

        if (shop.getShopName() != null) existingShop.setShopName(shop.getShopName());
        if (shop.getOwnerFname() != null) existingShop.setOwnerFname(shop.getOwnerFname());
        if (shop.getOwnerLname() != null) existingShop.setOwnerLname(shop.getOwnerLname());
        if (shop.getHouseNumber() != null) existingShop.setHouseNumber(shop.getHouseNumber());
        if (shop.getMoo() != null) existingShop.setMoo(shop.getMoo());
        if (shop.getStreet() != null) existingShop.setStreet(shop.getStreet());
        if (shop.getTumbon() != null) existingShop.setTumbon(shop.getTumbon());
        if (shop.getAmper() != null) existingShop.setAmper(shop.getAmper());
        if (shop.getProvince() != null) existingShop.setProvince(shop.getProvince());
        if (shop.getPhone() != null) existingShop.setPhone(shop.getPhone());
        if (shop.getPassword() != null) existingShop.setPassword(shop.getPassword());

        return shopRepository.save(existingShop);
    }

    public Shops getShopById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }
}
