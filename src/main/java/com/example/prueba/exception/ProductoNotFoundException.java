package com.example.prueba.exception;

public class ProductoNotFoundException extends RuntimeException {


    public ProductoNotFoundException(String message) {
        super(message);
    }
}
