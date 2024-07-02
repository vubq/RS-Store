package com.vubq.rsstore.services.impl;

import com.vubq.rsstore.entities.User;
import com.vubq.rsstore.repositories.UserRepository;
import com.vubq.rsstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return this.userRepository.existsByUserName(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(String id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
