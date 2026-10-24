package com.projeto.vendora.dto;

import com.projeto.vendora.enums.OrderStatus;
import com.projeto.vendora.enums.PaymentMethod;
import com.projeto.vendora.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDto(
        UUID id,
        OrderStatus orderStatus,
        PaymentMethod paymentMethod,
        PaymentStatus paymentStatus,
        BigDecimal total,
        LocalDateTime createdAt,
        List<OrderItemResponseDto> items
) {}
