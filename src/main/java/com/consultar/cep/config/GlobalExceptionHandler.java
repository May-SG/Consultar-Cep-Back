package com.consultar.cep.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("com.consultar.cep.controller")
public class GlobalExceptionHandler  {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Error:" + ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handlerGeneral(Exception ex) {
        return new ResponseEntity<>("Error:" + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
