package com.ivoyant.restaurantapp.exception;

import com.ivoyant.restaurantapp.dto.ErrorResponse;

public class CustomException extends RuntimeException{
    ErrorResponse errorResponse;

    public CustomException(ErrorResponse errorResponse){
        this.errorResponse=errorResponse;
    }

    public ErrorResponse getErrorResponse(){
        return errorResponse;
    }
}
