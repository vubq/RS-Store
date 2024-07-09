package com.vubq.rsstore.controllers;

import com.vubq.rsstore.entities.User;
import com.vubq.rsstore.services.UserService;
import com.vubq.rsstore.utils.DataTableRequest;
import com.vubq.rsstore.utils.DataTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/webapi/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/staff-get-all-page")
    public DataTableResponse staffGetAllPage(DataTableRequest request) {
        Page<User> results = this.userService.staffGetAllPage(request);
        return DataTableResponse.build().ok()
                .totalRows(results.getTotalElements())
                .items(results.getContent());
    }

    @PostMapping("/customer-online-get-all-page")
    public DataTableResponse customerOnlineGetAllPage(DataTableRequest request) {
        Page<User> results = this.userService.customerOnlineGetAllPage(request);
        return DataTableResponse.build().ok()
                .totalRows(results.getTotalElements())
                .items(results.getContent());
    }
}
