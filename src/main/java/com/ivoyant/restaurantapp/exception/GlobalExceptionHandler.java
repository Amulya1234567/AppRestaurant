package com.ivoyant.restaurantapp.exception;

import com.ivoyant.restaurantapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler
    public ResponseEntity<?> handler(CustomException exception){
        ErrorResponse errorResponse=exception.getErrorResponse();
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
