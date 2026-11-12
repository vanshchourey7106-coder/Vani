package com.vani.controller;


import com.vani.dto.AdminUserResponse;
import com.vani.service.AdminService;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }


    @GetMapping("/dashboard")
    public Map<String, String> adminDashboard(){
        return Map.of(
                "message","Welcome to Vani admin Dashboard"
        );
    }

    @GetMapping("/getall")
    public List<AdminUserResponse> getAllUsers(){
        return adminService.getAllUsers();

    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable String userId){
        return adminService.deleteUser(userId);

    }


}
