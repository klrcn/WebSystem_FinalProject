package com.digitalyearbook.Digital.Yearbook.controller;

import com.digitalyearbook.Digital.Yearbook.entity.User;
import com.digitalyearbook.Digital.Yearbook.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam String password) {

        if(userService.findByEmail(email).isPresent()) return "Email already registered!";
        if(userService.findByPhone(phone).isPresent()) return "Phone number already registered!";

        User user = new User(email, phone, password);
        userService.register(user);
        return "Registration successful! You can now login.";
    }

    @PostMapping("/login")
    public String login(@RequestParam String emailOrPhone,
                        @RequestParam String password) {

        Optional<User> user = userService.login(emailOrPhone, password);
        return user.map(u -> "Login successful! Welcome " + u.getEmail())
                .orElse("Invalid email/phone or password");
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String email,
                                 @RequestParam String password) {

        Optional<User> user = userService.findByEmail(email);
        if(user.isPresent()) {
            userService.updatePassword(email, password);
            return "Password updated successfully!";
        }
        return "Email not found!";
    }
}
