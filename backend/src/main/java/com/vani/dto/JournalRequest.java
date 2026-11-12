package com.vani.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JournalRequest {
    @NotBlank(message = "title cannot be blank")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    private String content;


}
