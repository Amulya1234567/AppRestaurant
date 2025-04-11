package com.ivoyant.restaurantapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Data
public class ErrorResponse {
    private String errorMessage;
    private String errorDetails;
    private int errorCode;

}
