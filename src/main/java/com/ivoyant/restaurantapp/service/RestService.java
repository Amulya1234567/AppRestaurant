package com.ivoyant.restaurantapp.service;

import com.ivoyant.restaurantapp.dto.Restaurant;

import com.ivoyant.restaurantapp.exception.DuplicateProductException;
import com.ivoyant.restaurantapp.exception.FoodNotFoundException;
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
    RestaurantJDBCRepo restaurantJDBCRepo;

    public ResponseEntity<Object> save(Restaurant restaurant) {
//       HashMap<String,Object> hashMap=new HashMap<>();
        if(restaurantJDBCRepo.findByFood(restaurant.getFood()).isEmpty()){
            int count =restaurantJDBCRepo.save(restaurant);
            if (count > 0) {
                return new ResponseEntity<>("Restaurant saved successfully", HttpStatus.CREATED);
            }
        }
        throw new DuplicateProductException("food is already present");

    }

//        hashMap.put("Menu", "Saved");
//        hashMap.put("Food",restaurant);
//        return new ResponseEntity<>(savedRestaurant,HttpStatus.CREATED);

    public ResponseEntity<Object> getFood() {
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
    public ResponseEntity<Object> getFoodById(int id) {
        List<Restaurant> lst = restaurantJDBCRepo.findById(id);
        if (lst.isEmpty()) {
             throw new FoodNotFoundException("Food not found");
//            System.out.println("Food Not Found");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(lst, HttpStatus.CREATED);

        }

    }
//
    public ResponseEntity<Object> getFoodByName(String name) {
        List<Restaurant> food = restaurantJDBCRepo.findByFood(name);
        if (food.isEmpty()) {
            System.out.println("Food Not Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        else{
            return new ResponseEntity<>(food, HttpStatus.CREATED);

        }
    }

    public ResponseEntity<Object> deleteById(int id) {
        int count =restaurantJDBCRepo.delete(id);
        if (count > 0) {
            return new ResponseEntity<>(count, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> updateById(int id, Restaurant restaurant) {
        int count =restaurantJDBCRepo.update(id,restaurant);
        if (count > 0) {
            return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
