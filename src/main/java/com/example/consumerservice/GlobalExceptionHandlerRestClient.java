package com.example.consumerservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

@RestControllerAdvice
public class GlobalExceptionHandlerRestClient {

  @ExceptionHandler(RestClientException.class)
  public ResponseEntity<?> handle(RestClientException e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(e.getMessage());
  }
}