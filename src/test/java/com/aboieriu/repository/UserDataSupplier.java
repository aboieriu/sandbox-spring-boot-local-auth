package com.aboieriu.repository;

import com.aboieriu.entity.User;

import java.util.UUID;

/**
 * @author aboieriu
 */
public class UserDataSupplier {

	public static User generateRandomUser(){
		User user = new User();
		user.setUsername(UUID.randomUUID().toString());
		user.setFirstName(UUID.randomUUID().toString());
		user.setLastName(UUID.randomUUID().toString());
		user.setPassword(UUID.randomUUID().toString());

		return user;
	}
}
