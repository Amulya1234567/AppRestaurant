package com.ivoyant.restaurantapp.service;

import com.ivoyant.restaurantapp.dto.ErrorResponse;
import com.ivoyant.restaurantapp.dto.Restaurant;
import com.ivoyant.restaurantapp.exception.CustomException;
import com.ivoyant.restaurantapp.repository.RestaurantJDBCRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestServiceTest {

    @Mock
    RestaurantJDBCRepo restaurantJDBCRepo;
    // The real RestService instance will be created with restaurantJDBCRepo injected as a mock.
    @InjectMocks
    RestService restService;

    @Test
    public void saveShouldSaveFoodSuccessfully() {
        // Create a sample Restaurant object for successful saving
        Restaurant restaurant = new Restaurant();
        restaurant.setFood("Burger");
        restaurant.setCategory("Fast Food");
        restaurant.setPrice(99.99);

        // findByFood to return an empty list no restaurant with the same food exists
        when(restaurantJDBCRepo.findByFood("Burger"))
                .thenReturn(Collections.emptyList());

        // the save method to return the same Restaurant object.
        when(restaurantJDBCRepo.save(restaurant))
                .thenReturn(restaurant);

        // Call the save method on the service.
        ResponseEntity<?> response = restService.save(restaurant);

        // Assert that the HTTP status code is 201 (Created).
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());


        assertEquals(restaurant, response.getBody());

        System.out.println("Successful save test completed.");
    }

    @Test
    public void saveShouldThrowDuplicateProductException() {
        // create a sample restaurant object to simulate a duplicate entry
        Restaurant restaurant = new Restaurant();
        restaurant.setFood("Chicken");
        restaurant.setCategory("Non-Veg");
        restaurant.setPrice(170);

        // findByFood to return a non-empty list, simulating an existing entry
        when(restaurantJDBCRepo.findByFood("Chicken"))
                .thenReturn(Collections.singletonList(restaurant));

        // call save on the service and verify it throws a CustomException
        try {
            restService.save(restaurant);
            // If no exception is thrown, fail the test.
            org.junit.jupiter.api.Assertions.fail("Expected CustomException to be thrown.");
        } catch (CustomException e) {

            // the exception contains an ErrorResponse
            ErrorResponse errorResponse = e.getErrorResponse();
            assertEquals("food is already present", errorResponse.getErrorMessage());

            assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getErrorCode());
            assertEquals(HttpStatus.NOT_FOUND.toString(), errorResponse.getErrorDetails());
        }
    }
}
