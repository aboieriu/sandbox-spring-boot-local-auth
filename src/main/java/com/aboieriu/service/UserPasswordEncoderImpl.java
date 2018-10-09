package com.aboieriu.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author aboieriu
 */
@Component
public class UserPasswordEncoderImpl implements UserPasswordEncoder {

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Override
	public String encode(String original) {
		return encoder.encode(original);
	}

	public boolean matches(String hash, String original){
		return encoder.matches(original, hash);
	}
}
