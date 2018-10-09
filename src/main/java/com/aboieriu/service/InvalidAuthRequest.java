package com.aboieriu.service;

/**
 * @author aboieriu
 */
public class InvalidAuthRequest extends RuntimeException {

	public InvalidAuthRequest() {
		super("Auth request failed. Credentials provided are either wrong or the user does not exist");
	}
}
