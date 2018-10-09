package com.aboieriu.adapter;

import com.aboieriu.domain.AuthResponseDto;
import com.aboieriu.domain.NewUserRequestDto;
import com.aboieriu.domain.RightCodeEnum;
import com.aboieriu.domain.UserDto;
import com.aboieriu.entity.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aboieriu
 */
@Component
public class UserAdapter {

	public UserDto toUserDto(User user) {
		return new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName());
	}

	public List<UserDto> toUserDtos(List<User> userDtoList) {
		return userDtoList.stream().map(this::toUserDto).collect(Collectors.toList());
	}

	public User toUser(NewUserRequestDto userRequestDto) {
		return new User(userRequestDto.getUsername(), userRequestDto.getPassword(), userRequestDto.getFirstName(), userRequestDto.getLastName());
	}

	public AuthResponseDto authResponseDto(User user) {
		UserDto userDto = toUserDto(user);
		return new AuthResponseDto(userDto, user.getActiveAuthToken().get().getToken(), Arrays.asList(RightCodeEnum.values()));
	}
}
