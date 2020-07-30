package com.br.falemaisapi.config;

import com.br.falemaisapi.exception.BadRequestException;
import com.br.falemaisapi.exception.ErrorResponse;
import com.br.falemaisapi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<Object> handleException(Exception ex) {
        ErrorResponse errorRes = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorRes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public final ResponseEntity<Object> handleUUnauthorizedException(BadRequestException ex) {
        ErrorResponse errorRes = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public final ResponseEntity<Object> handleUUnauthorizedException(NotFoundException ex) {
        ErrorResponse errorRes = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorRes, HttpStatus.NOT_FOUND);
    }
}
