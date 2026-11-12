package com.vani.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "journals")
public class Journal {
    @Id
    private String id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100 ,message = "Title cannot exceed 100 characters")
    private String title;


    private String content;

    private String userId;

}
