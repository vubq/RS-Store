package com.vubq.rsstore.services;

import com.vubq.rsstore.entities.Role;
import com.vubq.rsstore.enums.ERole;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(ERole name);
}
