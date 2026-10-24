package com.projeto.vendora.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemRequestDto(

        @NotNull(message = "O id do produto é obrigatório")
        UUID productId,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity
) {}