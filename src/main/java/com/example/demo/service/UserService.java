package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User getUserById(Long id){
    return userRepository.findById(id).orElseThrow();
}

    public User saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}