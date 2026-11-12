package com.vani.service;

import com.vani.dto.LoginRequest;
import com.vani.dto.LoginResponse;
import com.vani.dto.RegisterRequest;
import com.vani.dto.UserRegisterResponse;
import com.vani.entity.User;
import com.vani.enums.Role;
import com.vani.exception.InvalidCredentialsException;
import com.vani.exception.UserNotFoundException;
import com.vani.exception.UsernameAlreadyExistsException;
import com.vani.repository.UserRepository;
import com.vani.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder , JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }


    public String createUser(RegisterRequest registerRequest){
        if(userRepository.existsByUserName(registerRequest.getUserName())){
            throw new UsernameAlreadyExistsException("Username already exists");

        }

        User user = new User();

        user.setUserName(registerRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        return "User Saved";
    }

    public UserRegisterResponse getUserByUsername(String userName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();

        userRegisterResponse.setUserName(user.getUserName());

        return userRegisterResponse;
    }

    public LoginResponse login(LoginRequest loginRequest){
       User user =  userRepository.findByUserName(loginRequest.getUserName())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid Username or Password"));

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("Invalid Username or Password");
        }

        String token = jwtService.generateToken(user.getUserName(),
                user.getRole());
        return new LoginResponse(token);


    }


}
