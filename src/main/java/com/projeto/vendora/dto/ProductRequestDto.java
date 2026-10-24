package com.projeto.vendora.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDto(
        @NotBlank(message = "Product name is necessary")
        String name,
        @NotNull(message = "Product price is necessary")
        BigDecimal price,
        String description,
        @NotNull(message = "Product quantity is necessary")
        @Min(value = 0, message = "Quantity cannot be negative")
        Integer quantity,
        @NotNull(message = "Seller id is necessary")
        UUID sellerId
) {}
