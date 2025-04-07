package com.ivoyant.restaurantapp.exception;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String message) {
        super(message);
    }
}
