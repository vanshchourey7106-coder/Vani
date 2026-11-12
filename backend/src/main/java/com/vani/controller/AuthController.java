package com.vani.controller;

import com.vani.dto.LoginRequest;
import com.vani.dto.LoginResponse;
import com.vani.dto.RegisterRequest;
import com.vani.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse loginRequest(@RequestBody @Valid LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}