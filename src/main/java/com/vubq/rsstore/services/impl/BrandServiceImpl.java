package com.vubq.rsstore.services.impl;

import com.vubq.rsstore.entities.Brand;
import com.vubq.rsstore.repositories.BrandRepository;
import com.vubq.rsstore.services.BrandService;
import com.vubq.rsstore.utils.BaseSpecification;
import com.vubq.rsstore.utils.DataTableRequest;
import com.vubq.rsstore.utils.SearchCriteria;
import com.vubq.rsstore.utils.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Page<Brand> getAllPage(DataTableRequest request) {
        PageRequest pageable = request.toPageable();
        BaseSpecification<Brand> specNameContains = new BaseSpecification<>(
                SearchCriteria.builder().keys(new String[]{Brand.Fields.name}).operation(SearchOperation.CONTAINS)
                        .value(request.getFilter().trim().toUpperCase()).build());
        return this.brandRepository.findAll(Specification.where(specNameContains), pageable);
    }

    @Override
    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> getById(String id) {
        return this.brandRepository.findById(id);
    }
}
