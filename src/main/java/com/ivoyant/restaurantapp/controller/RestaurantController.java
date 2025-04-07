package com.ivoyant.restaurantapp.controller;

import com.ivoyant.restaurantapp.dto.Restaurant;
import com.ivoyant.restaurantapp.service.RestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Restaurant")
public class RestaurantController {
    @Autowired
    RestService restService;


    @Operation(summary="To enter a food menu")
    @PostMapping("/food")
    public ResponseEntity<Object> postFood(@RequestBody Restaurant restaurant){
         return restService.save(restaurant);
    }

    @GetMapping("/food")
    public ResponseEntity<Object> getFood(){
        return restService.getFood();
    }
//
    @GetMapping("/getFoodById/{id}")
    public ResponseEntity<Object> getFoodById(@PathVariable int id){
        return restService.getFoodById(id);
    }
//
    @GetMapping("/getFoodByName/{name}")
    public ResponseEntity<Object> getFoodByName(@PathVariable String name){
        return restService.getFoodByName(name);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id){
        return restService.deleteById(id);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<Object> updateById(@PathVariable int id,@RequestBody Restaurant restaurant){
        return restService.updateById(id,restaurant);
    }

}
