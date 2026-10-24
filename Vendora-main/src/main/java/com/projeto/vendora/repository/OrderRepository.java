package com.projeto.vendora.repository;

import com.projeto.vendora.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByBuyerId(UUID buyerId);
}