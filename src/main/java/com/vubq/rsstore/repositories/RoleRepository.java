package com.vubq.rsstore.repositories;

import com.vubq.rsstore.entities.Role;
import com.vubq.rsstore.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(ERole name);
}

