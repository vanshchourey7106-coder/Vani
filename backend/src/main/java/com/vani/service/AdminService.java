package com.vani.service;


import com.vani.dto.AdminUserResponse;
import com.vani.entity.User;
import com.vani.enums.Role;
import com.vani.exception.AdminOperationNotAllowedException;
import com.vani.exception.UserNotFoundException;
import com.vani.repository.JournalRepository;
import com.vani.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;

    public AdminService(UserRepository userRepository, JournalRepository journalRepository){
        this.userRepository = userRepository;
        this.journalRepository = journalRepository;


    }
    public List<AdminUserResponse> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(user -> new AdminUserResponse(user.getId(),
                        user.getUserName(),
                        user.getRole()
                ))
                .toList();

    }

    public String deleteUser(String userId){
        String currentAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not Found"));

        if(user.getUserName().equals(currentAdmin)){
            throw new AdminOperationNotAllowedException("Admin cannot delete their own account");


        }

        if(user.getRole() == Role.ADMIN && userRepository.countByRole(Role.ADMIN) <= 1){
            throw new AdminOperationNotAllowedException("Admin cannot delete their own account");

        }
        journalRepository.deleteByUserId(userId);
        userRepository.delete(user);

        return "User Deleted";

    }
}
