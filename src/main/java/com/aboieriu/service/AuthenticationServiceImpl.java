package com.aboieriu.service;

import com.aboieriu.adapter.UserAdapter;
import com.aboieriu.domain.AuthResponseDto;
import com.aboieriu.domain.UserAuthRequestDto;
import com.aboieriu.entity.AuthToken;
import com.aboieriu.entity.User;
import com.aboieriu.repository.AuthTokenRepository;
import com.aboieriu.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author aboieriu
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserPasswordEncoder userPasswordEncoder;

	@Resource
	private AuthTokenService authTokenService;

	@Resource
	private UserAdapter userAdapter;

	public AuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto) {
		Optional<User> targetUser = userRepository.findByUsername(userAuthRequestDto.getUsername());
		if (targetUser.isPresent()) {
			User usr = targetUser.get();
			if (userPasswordEncoder.matches(usr.getPassword(), userAuthRequestDto.getPassword())){
				authTokenService.createOrGetTokenForUser(usr);
				return userAdapter.authResponseDto(usr);
			}
		}
		throw new InvalidAuthRequest();
	}
}
