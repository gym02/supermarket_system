package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */
public class CheckPasswordNotSameException extends RuntimeException{
    public CheckPasswordNotSameException() {
    }

    public CheckPasswordNotSameException(String message) {
        super(message);
    }
}
