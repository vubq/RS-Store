package com.vubq.rsstore.services.impl;

import com.vubq.rsstore.entities.Role;
import com.vubq.rsstore.entities.User;
import com.vubq.rsstore.enums.ERole;
import com.vubq.rsstore.repositories.UserRepository;
import com.vubq.rsstore.services.UserService;
import com.vubq.rsstore.utils.DataTableRequest;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Page<User> staffGetAllPage(DataTableRequest request) {
        PageRequest pageable = request.toPageable();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(criteriaBuilder.concat(criteriaBuilder.concat(root.get(User.Fields.firstName), " "), root.get(User.Fields.lastName))), "%" + request.getFilter().trim().toUpperCase() + "%"));
                Join<User, Role> userRoleJoin = root.join("roles", JoinType.LEFT);
                predicates.add(criteriaBuilder.and(userRoleJoin.get(Role.Fields.name).in(ERole.ROLE_CUSTOMER).not()));
                query.where(predicates.toArray(new Predicate[]{}));
                return null;
            }
        };
        return this.userRepository.findAll(specification, pageable);
    }

    @Override
    public Page<User> customerOnlineGetAllPage(DataTableRequest request) {
        PageRequest pageable = request.toPageable();
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(criteriaBuilder.upper(criteriaBuilder.concat(criteriaBuilder.concat(root.get(User.Fields.firstName), " "), root.get(User.Fields.lastName))), "%" + request.getFilter().trim().toUpperCase() + "%"));
                Join<User, Role> userRoleJoin = root.join("roles", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(userRoleJoin.get(Role.Fields.name), ERole.ROLE_CUSTOMER));
                query.where(predicates.toArray(new Predicate[]{}));
                return null;
            }
        };
        return this.userRepository.findAll(specification, pageable);
    }
}
