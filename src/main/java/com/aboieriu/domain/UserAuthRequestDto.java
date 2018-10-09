package com.aboieriu.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aboieriu
 */
public class UserAuthRequestDto {
	private final String username;
	private final String password;

	@JsonCreator
	public UserAuthRequestDto(@JsonProperty("username") String username, @JsonProperty("password")String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
