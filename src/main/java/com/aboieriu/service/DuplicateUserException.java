package com.aboieriu.service;


import java.text.MessageFormat;

/**
 * @author aboieriu
 */
public class DuplicateUserException extends RuntimeException {

	private static final String MESSAGE_PATTERN = "User {0} already exists";

	public DuplicateUserException(String username) {
		super(MessageFormat.format(MESSAGE_PATTERN, username));
	}
}
