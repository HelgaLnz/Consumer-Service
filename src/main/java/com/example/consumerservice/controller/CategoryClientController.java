package com.example.consumerservice.controller;

import com.example.consumerservice.service.CategoryService;
import com.example.consumerservice.dto.CategoryDto;
import com.example.consumerservice.dto.PageableResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class CategoryClientController {

  private final CategoryService categoryService;

  @GetMapping("/categories/{id}")
  public ResponseEntity<CategoryDto> getByIdCategory(@PathVariable Integer id) {
    return new ResponseEntity<>(categoryService.getByIdCategory(id), HttpStatus.OK);
  }

  @GetMapping("/categories")
  public ResponseEntity<PageableResponseDto<CategoryDto>> getAllCategory(
    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") Integer limit
  ) {
    return categoryService.getAll(offset, limit);
  }

  @PostMapping("/categories")
  public ResponseEntity<String> createCategory(@RequestBody CategoryDto category) {
    return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
  }

  @PutMapping("/categories/{id}")
  public ResponseEntity<CategoryDto> changeCategory(@RequestBody CategoryDto category, @PathVariable Integer id) {
    return categoryService.change(category, id);
  }

  @DeleteMapping("/categories/{id}")
  public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Integer id) {
    return categoryService.delete(id);
  }

  @ExceptionHandler(RestClientException.class)
  public ResponseEntity<?> handle(RestClientException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(e.getMessage());
  }
}
