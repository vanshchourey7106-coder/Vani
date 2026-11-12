package com.vani.exception;

public class JournalNotFoundException extends RuntimeException{
    public JournalNotFoundException(String message){
        super(message);
    }
}