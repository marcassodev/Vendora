package com.projeto.vendora.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDto(
        UUID productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {}