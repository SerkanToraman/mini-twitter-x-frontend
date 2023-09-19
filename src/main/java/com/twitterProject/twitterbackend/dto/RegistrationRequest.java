package com.twitterProject.twitterbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    @NotNull
    @NotBlank
    @Size(min = 3, message = "Name size must be minimum 3")
    private String name;
    @NotNull
    @NotBlank
    @Size(min = 3, message = "Username size must be minimum 3")
    private String userName;
    @NotNull
    @NotBlank
    @Email(message ="A valid e-mail must be entered")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 8, message = "Password size must be minimum 8")
    private String password;
}
