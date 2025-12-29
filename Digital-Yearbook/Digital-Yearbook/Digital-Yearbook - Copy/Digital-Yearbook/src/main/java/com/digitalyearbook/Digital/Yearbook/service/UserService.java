package com.digitalyearbook.Digital.Yearbook.service;

import com.digitalyearbook.Digital.Yearbook.entity.User;

import java.util.Optional;

public interface UserService {
    void register(User user);
    Optional<User> login(String emailOrPhone, String password);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    void updatePassword(String email, String password);
}
