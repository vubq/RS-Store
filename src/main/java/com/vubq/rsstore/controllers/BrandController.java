package com.vubq.rsstore.controllers;

import com.vubq.rsstore.entities.Brand;
import com.vubq.rsstore.services.BrandService;
import com.vubq.rsstore.utils.DataTableRequest;
import com.vubq.rsstore.utils.DataTableResponse;
import com.vubq.rsstore.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/get-all-page")
    public DataTableResponse getAllPage(DataTableRequest request) {
        Page<Brand> results = this.brandService.getAllPage(request);
        return DataTableResponse.build().ok()
                .totalRows(results.getTotalElements())
                .items(results.getContent());
    }

    @PostMapping("create-or-update")
    public Response createOrUpdate(@RequestBody Brand brand) {
        brand.setCreatedAt(new Date());
        this.brandService.save(brand);
        return Response.build().ok();
    }

    @GetMapping("{id}")
    public Response getBrandById(@PathVariable String id) {
        return Response.build().ok()
                .data(this.brandService.getById(id).get());
    }
}
