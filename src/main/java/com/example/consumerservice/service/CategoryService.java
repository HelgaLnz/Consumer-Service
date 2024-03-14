package com.example.consumerservice.service;

import com.example.consumerservice.dto.CategoryDto;
import com.example.consumerservice.dto.PageableResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class CategoryService {

  @Value("${supplier.url}")
  private String supplierUrl;

  private final String CATEGORY_PATH = "/categories";

  private final RestTemplate template;

  public CategoryDto getByIdCategory(Integer id) {
    return template.getForObject(supplierUrl + CATEGORY_PATH + "/" + id, CategoryDto.class);
  }

  public ResponseEntity<PageableResponseDto<CategoryDto>> getAll(Integer offset, Integer limit) {
    return template.
      exchange(supplierUrl + CATEGORY_PATH + "?offset=" + offset + "&limit=" + limit,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<>() {
        });
  }

  public String create(CategoryDto category) {
    return template.postForObject(supplierUrl + CATEGORY_PATH, category, String.class);
  }

  public ResponseEntity<CategoryDto> change(CategoryDto category, Integer id) {
    return template.exchange(supplierUrl + CATEGORY_PATH + "/" + id,
      HttpMethod.PUT,
      new HttpEntity<>(category),
      CategoryDto.class);
  }

  public ResponseEntity<CategoryDto> delete(Integer id) {
    return template.exchange(supplierUrl + CATEGORY_PATH + "/" + id,
      HttpMethod.DELETE,
      HttpEntity.EMPTY,
      CategoryDto.class);
  }
}
