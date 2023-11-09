package com.example.supermarket.exception;

public class PasswordNotException extends RuntimeException {
    public PasswordNotException() {
    }

    public PasswordNotException(String message) {
        super(message);
    }

    public PasswordNotException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotException(Throwable cause) {
        super(cause);
    }

    public PasswordNotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
