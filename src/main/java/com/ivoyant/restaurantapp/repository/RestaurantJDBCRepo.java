package com.ivoyant.restaurantapp.repository;

import com.ivoyant.restaurantapp.dto.Restaurant;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RestaurantJDBCRepo {
    private final JdbcTemplate jdbcTemplate;

    public int save(Restaurant restaurant) {
        String sql = "INSERT INTO restaurant (food, category, price) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, restaurant.getFood(), restaurant.getCategory(), restaurant.getPrice());
    }

    public List<Restaurant> findAll() {
        String sql = "SELECT * FROM restaurant";
        return jdbcTemplate.query(sql, new RestaurantRowMapper());
    }

    public List<Restaurant> findById(int id) {
        String sql = "SELECT * FROM restaurant WHERE id=?";
        return jdbcTemplate.query(sql, new RestaurantRowMapper(),id);
    }

    public List<Restaurant> findByFood(String name){
        String sql="SELECT * FROM restaurant WHERE food=?";
        return jdbcTemplate.query(sql,new RestaurantRowMapper(), name);
    }


    public int delete(int id) {
        String sql="DELETE FROM restaurant WHERE id=?";
        return jdbcTemplate.update(sql,id);
    }

    public int update(int id, Restaurant restaurant) {
        String sql = "UPDATE restaurant SET food=?, category=?, price=? WHERE id=?";
        return jdbcTemplate.update(sql,
                restaurant.getFood(),
                restaurant.getCategory(),
                restaurant.getPrice(),
                id);
    }

}
