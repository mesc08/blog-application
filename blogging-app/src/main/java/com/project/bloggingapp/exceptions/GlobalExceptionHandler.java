package com.project.bloggingapp.exceptions;

import com.project.bloggingapp.utils.AppConstants;
import com.project.bloggingapp.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.NOTFOUND_STATUS_CODE, msg, false, null);
        return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)-> {
            String field = ((FieldError)error).getField();
            String msg = error.getDefaultMessage();
            response.put(field, msg);
        });
        ApiResponse apiResponse = new ApiResponse(AppConstants.BAD_STATUS_CODE, AppConstants.VALIDATION_ERROR, false, response);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse> IoExceptionHandler(IOException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.NOTFOUND_STATUS_CODE, msg, false, null);
        return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse> FileNotFoundHandler(FileNotFoundException ex){
        String msg = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.NOTFOUND_STATUS_CODE, msg, false, null);
        return new ResponseEntity(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<ApiResponse> MulitpartExceptionhandler(MultipartException exception){
        String msg = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(AppConstants.BAD_STATUS_CODE, msg, false, null);
        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
