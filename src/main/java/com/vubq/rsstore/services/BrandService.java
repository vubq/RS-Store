package com.vubq.rsstore.services;

import com.vubq.rsstore.entities.Brand;
import com.vubq.rsstore.utils.DataTableRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BrandService {

    Page<Brand> getAllPage(DataTableRequest request);

    Brand save(Brand brand);

    Optional<Brand> getById(String id);
}
