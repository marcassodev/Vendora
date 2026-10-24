package com.projeto.vendora.dto;

import com.projeto.vendora.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank(message = "User name is necessary")
        String name,
        @Email(message = "Invalid Email")
        String email,
        @NotBlank(message = "Password is necessary")
        String password,
        @NotBlank(message = "Phone is necessary")
        String phone,
        @NotNull(message = "User type is necessary")
        UserType userType
) {}
