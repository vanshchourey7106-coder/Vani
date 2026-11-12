package com.vani.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class JournalResponse {
    private String id;
    private String title;
    private String content;


}
