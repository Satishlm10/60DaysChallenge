package com._daysLearningChallenge.BestStore.Exceptions;

import com._daysLearningChallenge.BestStore.payloads.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponses> resourceNotFoundHandler(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponses apiResponses = new ApiResponses(msg,false);
        return new ResponseEntity<ApiResponses>(apiResponses, HttpStatus.NOT_FOUND) ;
    }
}
