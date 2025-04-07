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
    String errorMessage;
    String errorDetails;
    String errorCode;

//    public ErrorResponse(String errorMessage,String errorDetails,String errorCode){
//        this.timeStamp=LocalDateTime.now();
//        this.errorMessage =errorMessage;
//        this.errorDetails=errorDetails;
//        this.errorCode=errorCode;
//    }



}
