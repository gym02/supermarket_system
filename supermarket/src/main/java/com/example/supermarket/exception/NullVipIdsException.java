package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */
public class NullVipIdsException extends RuntimeException{
    public NullVipIdsException() {
    }

    public NullVipIdsException(String message) {
        super(message);
    }
}
