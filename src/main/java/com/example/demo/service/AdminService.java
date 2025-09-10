package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String name, String password) {
        Optional<Admin> admin = adminRepository.findByName(name);
        return admin.isPresent() && admin.get().getPassword().equals(password);
    }
}
