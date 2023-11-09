package com.example.supermarket.exception;

/**
 * @author 高垚淼
 * @version 1.0
 */
public class FindPasswordCodeErrorException extends RuntimeException{
    public FindPasswordCodeErrorException() {
    }

    public FindPasswordCodeErrorException(String message) {
        super(message);
    }

    public FindPasswordCodeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindPasswordCodeErrorException(Throwable cause) {
        super(cause);
    }

    public FindPasswordCodeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
