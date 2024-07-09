package com.vubq.rsstore.services;

import com.vubq.rsstore.entities.User;
import com.vubq.rsstore.utils.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmail(String email);

    Optional<User> findById(String id);

    User save(User user);

    Page<User> staffGetAllPage(DataTableRequest request);

    Page<User> customerOnlineGetAllPage(DataTableRequest request);
}
