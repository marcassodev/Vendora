package com.projeto.vendora.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record OrderItemRequestDto(

        @NotNull(message = "Id product necessary")
        UUID productId,

        @NotNull(message = "Quantity value is necessary")
        @Min(value = 1, message = "The minimum quantity is 1")
        Integer quantity
) {}