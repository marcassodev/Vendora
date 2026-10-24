package com.projeto.vendora.dto;

import com.projeto.vendora.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequestDto(
        @NotNull(message = "O id do cliente é obrigatório")
        UUID buyerId,
        @NotEmpty(message = "O pedido deve ter pelo menos um item")
        @Valid
        List<OrderItemRequestDto> items,
        @NotNull(message = "A forma de pagamento é obrigatória")
        PaymentMethod paymentMethod
) {}
