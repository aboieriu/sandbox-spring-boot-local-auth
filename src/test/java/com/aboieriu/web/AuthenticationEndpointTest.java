package com.aboieriu.web;

import com.aboieriu.domain.AuthResponseDto;
import com.aboieriu.domain.UserAuthRequestDto;
import com.aboieriu.domain.UserDto;
import com.aboieriu.service.AuthenticationService;
import com.aboieriu.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;
import org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;

/**
 * @author aboieriu
 */
@RunWith(PowerMockRunner.class)
public class AuthenticationEndpointTest {

	@InjectMocks
	private AuthenticationEndpoint authenticationEndpoint;

	@Mock
	private AuthenticationService authenticationService;

	@Mock
	private UserService userService;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetUserList(){
		UserAuthRequestDto test = new UserAuthRequestDto("aa", "bbb");

		AuthResponseDto expectedResponse =new AuthResponseDto(new UserDto(UUID.randomUUID().toString(), test.getUsername(), null, null), UUID.randomUUID().toString(), new ArrayList<>());

		// Define mock behaviour
		when(authenticationService.authenticate(test)).thenReturn(expectedResponse);

		// Make the actual call
		AuthResponseDto actualResponse = authenticationEndpoint.authenticate(test);

		// Check result
		Assert.assertEquals("Should be equals",expectedResponse, actualResponse);
	}
}
