package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */

public class CustomerNotFundException extends RuntimeException{
    public CustomerNotFundException() {
    }

    public CustomerNotFundException(String message) {
        super(message);
    }
}
