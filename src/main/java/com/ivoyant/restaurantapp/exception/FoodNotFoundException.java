package com.ivoyant.restaurantapp.exception;

public class FoodNotFoundException extends RuntimeException{
    public FoodNotFoundException(String message) {
        super(message);
    }
}
