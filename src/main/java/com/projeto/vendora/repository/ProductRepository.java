package com.projeto.vendora.repository;

import com.projeto.vendora.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findBySellerId(UUID sellerId);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}