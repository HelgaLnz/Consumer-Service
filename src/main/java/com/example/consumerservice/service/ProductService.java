package com.example.consumerservice.service;

import com.example.consumerservice.dto.PageableResponseDto;
import com.example.consumerservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

  @Value("${supplier.url}")
  private String supplierUrl;

  private final String PRODUCT_PATH = "/products";


  private final RestTemplate template;

  public ProductDto create(ProductDto product) {
    return template.postForObject(supplierUrl + PRODUCT_PATH, product, ProductDto.class);
  }

  public ProductDto getById(Integer id) {
    return template.getForObject(supplierUrl + PRODUCT_PATH + "/" + id, ProductDto.class);
  }

  public ResponseEntity<PageableResponseDto<ProductDto>> getAll(Integer offset, Integer limit) {
    return template.exchange(supplierUrl + PRODUCT_PATH,
      HttpMethod.GET,
      HttpEntity.EMPTY,
      new ParameterizedTypeReference<>() {
      },
      Map.of("offset", offset, "limit", limit)
    );
  }

  public ResponseEntity<ProductDto> change(ProductDto product, Integer id) {
    return template.exchange(supplierUrl + PRODUCT_PATH + "/" + id,
      HttpMethod.PUT,
      new HttpEntity<>(product),
      ProductDto.class
    );
  }

  public ResponseEntity<ProductDto> delete(Integer id) {
    return template.exchange(supplierUrl + PRODUCT_PATH + "/" + id,
      HttpMethod.DELETE,
      HttpEntity.EMPTY,
      ProductDto.class
    );
  }
}
