package com.projeto.vendora.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDto(
        UUID id,
        String name,
        BigDecimal price,
        String description,
        Integer quantity,
        UUID sellerId
) {}
