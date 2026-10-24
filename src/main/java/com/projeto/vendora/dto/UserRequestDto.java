package com.projeto.vendora.dto;

import com.projeto.vendora.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotBlank(message = "O nome do usuário é obrigatório")
        String name,
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String password,
        @NotBlank(message = "O telefone é obrigatório")
        String phone,
        @NotNull(message = "O tipo de usuário é obrigatório")
        UserType userType
) {}
