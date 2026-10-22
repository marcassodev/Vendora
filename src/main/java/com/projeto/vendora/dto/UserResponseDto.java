package com.projeto.vendora.dto;

import com.projeto.vendora.enums.UserType;

import java.util.UUID;

public record UserResponseDto(
        UUID id,
        String name,
        String email,
        String password,
        String phone,
        UserType userType
) {}
