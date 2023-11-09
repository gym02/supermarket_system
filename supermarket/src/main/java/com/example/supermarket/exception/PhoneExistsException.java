package com.example.supermarket.exception;

public class PhoneExistsException extends  RuntimeException{
    public PhoneExistsException() {
    }

    public PhoneExistsException(String message) {
        super(message);
    }

    public PhoneExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneExistsException(Throwable cause) {
        super(cause);
    }

    public PhoneExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
