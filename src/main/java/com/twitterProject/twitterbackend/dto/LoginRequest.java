package com.twitterProject.twitterbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @NotBlank
    @Email(message ="E-mail is not valid")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 8, message = "Password size must be minimum 8")
    private String password;
}
