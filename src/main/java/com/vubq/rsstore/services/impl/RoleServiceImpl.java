package com.vubq.rsstore.services.impl;

import com.vubq.rsstore.entities.Role;
import com.vubq.rsstore.enums.ERole;
import com.vubq.rsstore.repositories.RoleRepository;
import com.vubq.rsstore.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(ERole name) {
        return this.roleRepository.findByName(name);
    }
}
