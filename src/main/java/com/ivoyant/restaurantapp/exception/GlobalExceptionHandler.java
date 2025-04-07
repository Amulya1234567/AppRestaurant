package com.ivoyant.restaurantapp.exception;

import com.ivoyant.restaurantapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ErrorResponse> duplicate(DuplicateProductException dpex, WebRequest webRequest) {
        ErrorResponse errorResponse=new ErrorResponse(dpex.getMessage(),
                webRequest.getDescription(false),
                "DUPLICATE PRODUCT FOUND"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FoodNotFoundException.class)
    public ResponseEntity<ErrorResponse> duplicate(FoodNotFoundException fnex, WebRequest webRequest) {
        ErrorResponse errorResponse=new ErrorResponse(fnex.getMessage(),
                webRequest.getDescription(false),
                "ID NOT PRESENT"
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
