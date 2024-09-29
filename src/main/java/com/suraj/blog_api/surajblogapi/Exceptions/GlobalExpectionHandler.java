package com.suraj.blog_api.surajblogapi.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.suraj.blog_api.surajblogapi.Payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExpectionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(
            ResourceNotFoundException resourceNotFoundException) {
        String message = resourceNotFoundException.getMessage();
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> resMap = new HashMap<>();
        // String meString = methodArgumentNotValidException.getMessage();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldString = ((FieldError) error).getField();
            String messageString = error.getDefaultMessage();
            resMap.put(fieldString, messageString);
        }

        );

        return new ResponseEntity<Map<String, String>>(resMap, HttpStatus.BAD_REQUEST);
    }

}
