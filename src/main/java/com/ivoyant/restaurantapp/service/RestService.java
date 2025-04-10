package com.ivoyant.restaurantapp.service;

import com.ivoyant.restaurantapp.dto.ErrorResponse;
import com.ivoyant.restaurantapp.dto.Restaurant;

import com.ivoyant.restaurantapp.exception.CustomException;
import com.ivoyant.restaurantapp.repository.RestaurantJDBCRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class RestService {
    @Autowired
    public RestaurantJDBCRepo restaurantJDBCRepo;

    public ResponseEntity<?> save(Restaurant restaurant) {
//       HashMap<String,Object> hashMap=new HashMap<>();
        if(restaurantJDBCRepo.findByFood(restaurant.getFood()).isEmpty()){
            Restaurant restaurant1=restaurantJDBCRepo.save(restaurant);
            return new ResponseEntity<>(restaurant1, HttpStatus.CREATED);
        }
        String message="food is already present";
        int errorCode=HttpStatus.NOT_FOUND.value();
        String errorDetails=HttpStatus.NOT_FOUND.toString();
        ErrorResponse errorResponse=new ErrorResponse(message,errorDetails,errorCode);
        throw new CustomException(errorResponse);

    }

//        hashMap.put("Menu", "Saved");
//        hashMap.put("Food",restaurant);
//        return new ResponseEntity<>(savedRestaurant,HttpStatus.CREATED);

    public ResponseEntity<?> getFood() {
//      HashMap<String,Object> hashMap=new HashMap<>();
        System.out.println("Get food details");
        List<Restaurant> lst=restaurantJDBCRepo.findAll();
        if(lst.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lst, HttpStatus.CREATED);
        }
    }
//
    public ResponseEntity<?> getFoodById(int id) {
        List<Restaurant> lst = restaurantJDBCRepo.findById(id);
        if (lst.isEmpty()) {
            String message="ID NOT PRESENT";
            int errorCode=HttpStatus.NOT_FOUND.value();
            String errorDetails=HttpStatus.NOT_FOUND.toString();
            ErrorResponse errorResponse=new ErrorResponse(message,errorDetails,errorCode);
            throw new CustomException(errorResponse);
        }
        else{
            return new ResponseEntity<>(lst, HttpStatus.CREATED);

        }

    }
//
    public ResponseEntity<?> getFoodByName(String name) {
        List<Restaurant> food = restaurantJDBCRepo.findByFood(name);
        if (food.isEmpty()) {
            System.out.println("Food Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        else{
            return new ResponseEntity<>(food, HttpStatus.CREATED);

        }
    }

    public ResponseEntity<?> deleteById(int id) {
        int count =restaurantJDBCRepo.delete(id);
        if (count > 0) {
            return new ResponseEntity<>(count, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<?> updateById(int id, Restaurant restaurant) {
        Restaurant restaurant1 =restaurantJDBCRepo.update(id,restaurant);
        if(restaurantJDBCRepo.findById(id).isEmpty()){
            return new ResponseEntity<>("Failed to update", HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(restaurant1, HttpStatus.CREATED);
        }



    }
}
