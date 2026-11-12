package com.vani.exception;


import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(JournalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    public Map<String,Object> handleJournalNotFound(JournalNotFoundException ex){
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 404,
                "error" , "not found",
                "message", ex.getMessage()
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,Object> handleUserNotFound(UserNotFoundException ex){
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 404,
                "error" , "not found",
                "message", ex.getMessage()

        );
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,Object> handleDuplicateUsername(UsernameAlreadyExistsException ex){
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status",409,
                "error" , "conflict",
                "message" , ex.getMessage()

        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Map<String,Object> handleInvalidCredentials(InvalidCredentialsException ex){
        return Map.of(
                "timestamp",LocalDateTime.now(),
                "status",401,
                "error" , "unauthorized",
                "message" , ex.getMessage()

        );
    }

    @ExceptionHandler(AdminOperationNotAllowedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,Object> handleAdminOperationNotAllowed(AdminOperationNotAllowedException ex){
        return Map.of(
                "timestamp",LocalDateTime.now(),
                "status" , 500,
                "error" , "Bad Request",
                "message", ex.getMessage()
        );
    }
}
