package com.myblog.myblog.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

