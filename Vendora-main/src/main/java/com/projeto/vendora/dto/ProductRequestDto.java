package com.projeto.vendora.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDto(
        @NotBlank(message = "O nome do produto é obirgatório")
        String name,
        @NotNull(message = "O preço do produto é obrigatório")
        BigDecimal price,
        String description,
        @NotNull(message = "A quantidade do produto é obrigatória")
        @Min(value = 0, message = "A quantidade não pode ser negativa")
        Integer quantity,
        @NotNull(message = "O id do vendedor é obrigatório")
        UUID sellerId
) {}
