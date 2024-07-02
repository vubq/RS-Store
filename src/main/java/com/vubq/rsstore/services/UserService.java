package com.vubq.rsstore.services;

import com.vubq.rsstore.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
    Optional<User> findById(String id);
    User save(User user);
}
