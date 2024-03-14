package com.example.consumerservice.dto;


import lombok.Builder;

import java.util.List;

@Builder
public record PageableResponseDto<T>(List<T> content, Integer totalElements) {
}
