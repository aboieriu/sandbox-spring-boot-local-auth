package com.aboieriu.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aboieriu
 */
public class AuthResponseDto {
	private final UserDto user;
	private final String token;
	private final List<RightCodeEnum> rights;

	public AuthResponseDto(UserDto user, String token, List<RightCodeEnum> rights) {
		this.user = user;
		this.token = token;
		this.rights = new ArrayList<>(rights);
	}

	public UserDto getUser() {
		return user;
	}

	public String getToken() {
		return token;
	}

	public List<RightCodeEnum> getRights() {
		return rights;
	}
}
