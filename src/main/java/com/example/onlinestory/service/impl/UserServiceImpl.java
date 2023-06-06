package com.example.onlinestory.service.impl;

import com.example.onlinestory.entity.User;
import com.example.onlinestory.entity.UserType;
import com.example.onlinestory.repository.CartRepository;
import com.example.onlinestory.repository.UserRepository;
import com.example.onlinestory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @Override
    public void findByUser(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            String encodePassword = user.getPassword();
            String encode = passwordEncoder.encode(encodePassword);
            user.setPassword(encode);
            user.setUserType(UserType.USER);
            userRepository.save(user);
        }
    }
}
