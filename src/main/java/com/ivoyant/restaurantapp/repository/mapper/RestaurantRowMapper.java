package com.ivoyant.restaurantapp.repository.mapper;

import com.ivoyant.restaurantapp.dto.Restaurant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantRowMapper implements RowMapper<Restaurant> {
    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(rs.getInt("id"));
        restaurant.setFood(rs.getString("food"));
        restaurant.setCategory(rs.getString("category"));
        restaurant.setPrice(rs.getDouble("price"));
        return restaurant;
    }
}
