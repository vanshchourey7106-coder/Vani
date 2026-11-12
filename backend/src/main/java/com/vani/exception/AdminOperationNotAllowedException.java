package com.vani.exception;

import com.vani.service.AdminService;

public class AdminOperationNotAllowedException extends RuntimeException{
    public AdminOperationNotAllowedException(String message){
        super(message);
    }
}
