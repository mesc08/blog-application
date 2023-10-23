package com.project.bloggingapp.exceptions;

import com.project.bloggingapp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    //@ExceptionHandler({ResourceNotFoundException.class, IllegalArgument.class})
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(404, "Resource not found", false, null);
        return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
    }
}
