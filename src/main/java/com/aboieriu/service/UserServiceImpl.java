package com.aboieriu.service;

import com.aboieriu.adapter.UserAdapter;
import com.aboieriu.domain.NewUserRequestDto;
import com.aboieriu.domain.UserDto;
import com.aboieriu.entity.User;
import com.aboieriu.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author aboieriu
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserAdapter userAdapter;

	@Resource
	private UserPasswordEncoder userPasswordEncoder;

	@Resource
	private UserValidator userValidator;

	@Override
	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		return userAdapter.toUserDtos(users);
	}

	@Override
	public UserDto createNewUser(NewUserRequestDto userRequestDto) {
		User user = userAdapter.toUser(userRequestDto);

		// Make sure to validate user before moving forward
		userValidator.validate(user);

		// Make sure to hash pwd before saving
		user.setPassword(userPasswordEncoder.encode(user.getPassword()));

		return userAdapter.toUserDto(userRepository.save(user));
	}
}
