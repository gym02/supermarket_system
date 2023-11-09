package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */
public class VipIsNullException extends RuntimeException{
    public VipIsNullException() {
    }

    public VipIsNullException(String message) {
        super(message);
    }
}
