package com.digitalyearbook.Digital.Yearbook.service;

import com.digitalyearbook.Digital.Yearbook.entity.User;
import com.digitalyearbook.Digital.Yearbook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> login(String emailOrPhone, String password) {

        return userRepository.findByEmailAndPassword(emailOrPhone, password)
                .or(() -> userRepository.findByPhoneAndPassword(emailOrPhone, password));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public void updatePassword(String email, String password) {
        userRepository.findByEmail(email).ifPresent(user -> {
            user.setPassword(password);
            userRepository.save(user);
        });
    }
}
