package com.aboieriu.service;

/**
 * @author aboieriu
 */
public interface UserPasswordEncoder {
	String encode(String original);
	boolean matches(String hash, String original);
}
