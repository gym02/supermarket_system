package com.example.supermarket.exception;

public class MenuReferenceException extends RuntimeException{

	public MenuReferenceException() {
	}

	public MenuReferenceException(String message) {
		super(message);
	}

	public MenuReferenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public MenuReferenceException(Throwable cause) {
		super(cause);
	}

	public MenuReferenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
