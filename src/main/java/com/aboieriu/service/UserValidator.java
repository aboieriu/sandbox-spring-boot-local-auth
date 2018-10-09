package com.aboieriu.service;

import com.aboieriu.entity.User;
import com.aboieriu.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author aboieriu
 */
@Component
public class UserValidator {

	@Resource
	private UserRepository userRepository;

	public void validate(User user) {
		boolean alreadyExist = userRepository.findByUsername(user.getUsername()).isPresent();
		// One validation rule - duplicate user
		if (alreadyExist) {
			throw new DuplicateUserException(user.getUsername());
		}
	}
}
