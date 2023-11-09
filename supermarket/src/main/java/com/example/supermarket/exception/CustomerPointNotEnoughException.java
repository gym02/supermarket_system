package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */
public class CustomerPointNotEnoughException extends RuntimeException {
    public CustomerPointNotEnoughException() {
    }

    public CustomerPointNotEnoughException(String message) {
        super(message);
    }
}
