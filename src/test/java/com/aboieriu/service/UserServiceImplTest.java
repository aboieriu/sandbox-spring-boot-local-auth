package com.aboieriu.service;

import com.aboieriu.adapter.UserAdapter;
import com.aboieriu.domain.UserDto;
import com.aboieriu.entity.User;
import com.aboieriu.repository.UserDataSupplier;
import com.aboieriu.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author aboieriu
 */
@RunWith(PowerMockRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Spy
	private UserAdapter userAdapter = new UserAdapter();

	@Mock
	private UserPasswordEncoder userPasswordEncoder;

	@Mock
	private UserValidator userValidator;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldReturnUsers(){
		// Initial data
		List<User> users = new ArrayList<>();

		users.add(UserDataSupplier.generateRandomUserWithId());
		users.add(UserDataSupplier.generateRandomUserWithId());
		users.add(UserDataSupplier.generateRandomUserWithId());

		// Define behaviour
		when(userRepository.findAll()).thenReturn(users);

		// Make the actual call
		List<UserDto> response = userService.getUsers();

		// Verify dependency call
		verify(userAdapter).toUserDtos(users);

		Assert.assertTrue("Response list size should be equal to input data size", response.size() == users.size());
	}

	@Test
	public void shouldCreateNewUser(){
		// Initial data


		User userCreated = UserDataSupplier.generateRandomUser();
	}

}
