package com.vubq.rsstore.repositories;

import com.vubq.rsstore.entities.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    Page<Brand> findAll(Specification<Brand> spec, Pageable pageable);
}
