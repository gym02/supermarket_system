package com.example.supermarket.exception;

public class UserNameNotExistException extends RuntimeException {
    public UserNameNotExistException() {
        super();
    }

    public UserNameNotExistException(String message) {
        super(message);
    }

    public UserNameNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameNotExistException(Throwable cause) {
        super(cause);
    }

    protected UserNameNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
