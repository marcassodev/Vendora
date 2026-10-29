package com.projeto.vendora.service;

import com.projeto.vendora.dto.ProductRequestDto;
import com.projeto.vendora.dto.ProductResponseDto;
import com.projeto.vendora.enums.UserType;
import com.projeto.vendora.exception.ProductAlreadyExistsException;
import com.projeto.vendora.exception.ResourceNotFoundException;
import com.projeto.vendora.exception.UnauthorizedSellerException;
import com.projeto.vendora.model.ProductEntity;
import com.projeto.vendora.model.UserEntity;
import com.projeto.vendora.repository.ProductRepository;
import com.projeto.vendora.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto findById(UUID id) {
        return productRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "The product with ID " + id + " was not found."));
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> findBySellerId(UUID sellerId) {
        if (!userRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException(
                    "The seller was not found with the ID: " + sellerId);
        }
        return productRepository.findBySellerId(sellerId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        if (productRepository.existsByNameIgnoreCase(productRequestDto.name())) {
            throw new ProductAlreadyExistsException(
                    "The product '" + productRequestDto.name() + " is already registered.");
        }
        UserEntity seller = userRepository.findById(productRequestDto.sellerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with ID: " + productRequestDto.sellerId()));

        if (seller.getUserType() != UserType.SELLER) {
            throw new UnauthorizedSellerException(
                    "The specified user is not a seller");
        }

        ProductEntity newProduct = ProductEntity.builder()
                .name(productRequestDto.name())
                .price(productRequestDto.price())
                .description(productRequestDto.description())
                .quantity(productRequestDto.quantity())
                .seller(seller)
                .build();

        ProductEntity savedProduct = productRepository.save(newProduct);
        return toDto(savedProduct);
    }

    @Transactional
    public ProductResponseDto update(UUID id, ProductRequestDto productRequestDto) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Product with ID " + id + " was not found"));

        if (!existingProduct.getName().equals(productRequestDto.name()) &&
                productRepository.existsByNameIgnoreCase(productRequestDto.name())) {
            throw new ProductAlreadyExistsException(
                    "The product '" + productRequestDto.name() + "' is already registered");
        }

        UserEntity seller = userRepository.findById(productRequestDto.sellerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User not found with the ID: " + productRequestDto.sellerId()));

        if (seller.getUserType() != UserType.SELLER) {
            throw new UnauthorizedSellerException(
                    "The specified user is not a seller");
        }

        existingProduct.setName(productRequestDto.name());
        existingProduct.setPrice(productRequestDto.price());
        existingProduct.setDescription(productRequestDto.description());
        existingProduct.setQuantity(productRequestDto.quantity());
        existingProduct.setSeller(seller);

        ProductEntity updatedProduct = productRepository.save(existingProduct);
        return toDto(updatedProduct);
    }

    @Transactional
    public void delete(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Product with ID " + id + " was not found");
        }

        productRepository.deleteById(id);
    }

    private ProductResponseDto toDto(ProductEntity product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantity(),
                product.getSeller().getId()
        );
    }
}
