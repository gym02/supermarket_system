package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */
public class VipIsExitsException extends RuntimeException {
    public VipIsExitsException() {
    }

    public VipIsExitsException(String message) {
        super(message);
    }
}
