package com.vsantos1.exceptions;

import com.vsantos1.dtos.ErrorsDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;

@RestControllerAdvice
public class ValidationExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsDTO handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {

        HashMap<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });


        return new ErrorsDTO(new Date(), "Validation error", HttpStatus.BAD_REQUEST.value(), errors, request.getRequestURI());

    }
}
