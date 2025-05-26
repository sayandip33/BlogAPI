package com.myblog.myblog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty
    @Size(min = 2, message = "Name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Email(message = "email format should be given")
    private String email;
    @NotEmpty
    @Size(min = 2, message = "body should have at least 2 characters")
    private String body;
}

