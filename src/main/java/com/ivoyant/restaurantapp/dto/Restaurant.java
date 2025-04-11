package com.ivoyant.restaurantapp.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String food;
    private String category;
    @Column(nullable = false)
    private double price;
}
