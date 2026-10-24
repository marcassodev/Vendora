package com.projeto.vendora.dto;

import com.projeto.vendora.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequestDto(
        @NotNull(message = "Client Id is necessary")
        UUID buyerId,
        @NotEmpty(message = "The order needs at least one product")
        @Valid
        List<OrderItemRequestDto> items,
        @NotNull(message = "Payment method is necessary")
        PaymentMethod paymentMethod
) {}
