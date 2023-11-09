package com.example.supermarket.exception;

public class VerifyCodeErrorException extends RuntimeException {
    public VerifyCodeErrorException() {
        super();
    }

    public VerifyCodeErrorException(String message) {
        super(message);
    }

    public VerifyCodeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyCodeErrorException(Throwable cause) {
        super(cause);
    }

    protected VerifyCodeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
