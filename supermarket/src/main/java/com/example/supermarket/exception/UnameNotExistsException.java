package com.example.supermarket.exception;

public class UnameNotExistsException extends RuntimeException{
	public UnameNotExistsException() {
	}

	public UnameNotExistsException(String message) {
		super(message);
	}

	public UnameNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnameNotExistsException(Throwable cause) {
		super(cause);
	}

	public UnameNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
