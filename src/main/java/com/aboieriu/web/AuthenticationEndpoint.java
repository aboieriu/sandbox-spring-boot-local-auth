package com.aboieriu.web;

import com.aboieriu.domain.AuthResponseDto;
import com.aboieriu.domain.NewUserRequestDto;
import com.aboieriu.domain.UserAuthRequestDto;
import com.aboieriu.domain.UserDto;
import com.aboieriu.service.AuthenticationService;
import com.aboieriu.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author aboieriu
 */
@RestController
@RequestMapping("/user")
public class AuthenticationEndpoint {

	@Resource
	private AuthenticationService authenticationService;

	@Resource
	private UserService userService;

	@GetMapping
	public List<UserDto> getUserList(){
		return userService.getUsers();
	}

	@PostMapping
	public UserDto createNewUser(@RequestBody NewUserRequestDto userRequestDto) {
		return userService.createNewUser(userRequestDto);
	}

	@PostMapping("/authenticate")
	public AuthResponseDto authenticate(@RequestBody UserAuthRequestDto userAuthRequestDto) {
		return authenticationService.authenticate(userAuthRequestDto);
	}

}
