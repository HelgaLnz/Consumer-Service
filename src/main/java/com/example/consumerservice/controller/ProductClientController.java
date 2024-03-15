package com.example.consumerservice.controller;

import com.example.consumerservice.dto.PageableResponseDto;
import com.example.consumerservice.dto.ProductDto;
import com.example.consumerservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")

public class ProductClientController {

  private final ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<?> createProduct(@RequestBody ProductDto product) {
    return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Integer id) {
    return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
  }

  @GetMapping("/products")
  public ResponseEntity<PageableResponseDto<ProductDto>> getAllProducts(
    @RequestParam(value = "offset", defaultValue = "0") Integer offset,
    @RequestParam(value = "limit", defaultValue = "5") Integer limit
  ) {
    return productService.getAll(offset, limit);
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<ProductDto> changeProduct(@RequestBody ProductDto product, @PathVariable Integer id) {
    return productService.change(product, id);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<ProductDto> deleteProduct(@PathVariable Integer id) {
    return productService.delete(id);
  }
}

