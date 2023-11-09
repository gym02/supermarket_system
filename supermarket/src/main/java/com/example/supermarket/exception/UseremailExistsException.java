package com.example.supermarket.exception;

public class UseremailExistsException extends RuntimeException{
	public UseremailExistsException() {
	}

	public UseremailExistsException(String message) {
		super(message);
	}

	public UseremailExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UseremailExistsException(Throwable cause) {
		super(cause);
	}

	public UseremailExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
