package com.example.capstoneweek3.Models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {
    @NotNull(message = "ID must not be null")
    @Positive(message = "id must be positive number")
    private Integer id;

    @NotEmpty(message = "username must not be empty")
    @Size(min = 5, message = "username must be  >= 5")
    private String username;

    @NotEmpty(message = "password must not be empty")
    @Size(min = 7, message = "password must be  >= 7")
    @Pattern(regexp = "^[a-zA-Z0-9]*$\n")
    private String password;

    @NotEmpty(message = "email must not be empty")
    @Email(message = "please enter email pattern")
    private String email;

    @NotEmpty(message = "role must not be empty")
    @Pattern(regexp = "(Admin,Cutomer)")
    private String role;

    @NotNull(message = "balance must not be null")
    @Positive(message = "balance must be positive number")
    private Double balance;
}
