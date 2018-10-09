package com.aboieriu.service;

import com.aboieriu.domain.NewUserRequestDto;
import com.aboieriu.domain.UserDto;

import java.util.List;

/**
 * @author aboieriu
 */
public interface UserService {
	List<UserDto> getUsers();
	UserDto createNewUser(NewUserRequestDto userRequestDto);
}
