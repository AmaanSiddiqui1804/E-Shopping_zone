package com.orderservice.app.exception;


public class CartNotFoundException extends RuntimeException {
	public CartNotFoundException(String message) {
		super(message);
	}
}
