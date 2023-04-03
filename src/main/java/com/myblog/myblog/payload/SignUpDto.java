package com.myblog.myblog.payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDto {
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;
    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "email should not empty")
    @Email
    private String email;
    @NotEmpty(message = "password should not empty")
    private String password;
}
