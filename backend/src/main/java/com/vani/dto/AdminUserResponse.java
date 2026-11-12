package com.vani.dto;


import com.vani.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminUserResponse {
    private String id;
    private String userName;
    private Role role;


}
