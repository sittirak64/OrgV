package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String register(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists";
        }
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }
        // TODO: Hash password ก่อนบันทึก
        userRepository.save(user);
        return "Register success";
    }

    public boolean login(String username, String passwordHash) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && user.get().getPasswordHash().equals(passwordHash);
    }
}

