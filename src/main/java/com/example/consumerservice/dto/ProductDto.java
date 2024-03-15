package com.example.consumerservice.dto;

import lombok.Builder;

@Builder
public record ProductDto(String name, String description, Double price, Integer categoryId) {
}
