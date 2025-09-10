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

    public String register(Shops shop) {
        if (shopRepository.findByUsername(shop.getUsername()).isPresent()) {
            return "Username already exists";
        }

        // TODO: Hash password ก่อนบันทึกจริง ๆ
        shopRepository.save(shop);
        return "Register success";
    }

    public boolean login(String username, String password) {
        Optional<Shops> shop = shopRepository.findByUsername(username);
        return shop.isPresent() && shop.get().getPassword().equals(password);
    }
}
