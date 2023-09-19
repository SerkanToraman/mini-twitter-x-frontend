package com.twitterProject.twitterbackend.exceptions;


import com.twitterProject.twitterbackend.entity.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GeneralResponse> handleException(GeneralException fruitException){
        GeneralResponse response = new GeneralResponse(fruitException.getStatus().value(),
                fruitException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, fruitException.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleException(MethodArgumentNotValidException exception){
        String firstErrorMessage = exception.getBindingResult().getFieldErrors()
                .stream()
                .findFirst()
                .map(fieldError -> fieldError.getDefaultMessage())
                .orElse("Validation error message not found");
        GeneralResponse response = new GeneralResponse(HttpStatus.BAD_REQUEST.value(),firstErrorMessage
                , System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    public ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){
//        List errorList = exception.getBindingResult().getFieldErrors().stream()
//                .map(fieldError -> {
//                    Map<String, String> errors = new HashMap<>();
//                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
//                    return errors;
//                }).collect(Collectors.toList());
//        return ResponseEntity.badRequest().body(errorList);
//    }

    @ExceptionHandler
    public ResponseEntity<GeneralResponse> handleException(Exception exception){
        GeneralResponse response = new GeneralResponse(HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}

