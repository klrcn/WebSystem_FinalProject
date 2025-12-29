package com.digitalyearbook.Digital.Yearbook.service;

import org.springframework.stereotype.Service;
import com.digitalyearbook.Digital.Yearbook.entity.User;
import com.digitalyearbook.Digital.Yearbook.repository.UserRepository;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepo;

    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Map<String, Object> register(User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            return Map.of(
                    "success", false,
                    "message", "Email already registered"
            );
        }

        userRepo.save(user);
        return Map.of(
                "success", true,
                "message", "Registered successfully"
        );
    }

    public Map<String, Object> login(String email, String password) {
        var found = userRepo.findByEmail(email);

        if (found.isEmpty()) {
            return Map.of(
                    "success", false,
                    "message", "You are not registered yet"
            );
        }

        User user = found.get();

        if (!user.getPassword().equals(password)) {
            return Map.of(
                    "success", false,
                    "message", "Wrong password"
            );
        }

        return Map.of(
                "success", true,
                "message", "Login successful",
                "userId", user.getId()
        );
    }
}
