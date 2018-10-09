package com.aboieriu.service;

import com.aboieriu.adapter.UserAdapter;
import com.aboieriu.domain.NewUserRequestDto;
import com.aboieriu.domain.UserDto;
import com.aboieriu.entity.User;
import com.aboieriu.repository.UserDataSupplier;
import com.aboieriu.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
		String encodedPassword = "encodedPassword";
		User userToBeCreated = UserDataSupplier.generateRandomUser();

		// Initial data
		User userCreated = UserDataSupplier.generateRandomUserWithId();

		// Input data
		NewUserRequestDto inputData = new NewUserRequestDto("aa", "bb", "cc", "dd");
		//userToBeCreated.setPassword(inputData.getPassword());

		when(userPasswordEncoder.encode(anyString())).thenReturn(encodedPassword);

		//when(userAdapter.toUser(inputData)).thenReturn(userToBeCreated);

		when(userRepository.save(userToBeCreated)).thenReturn(userCreated);



		// Make the actual call
		UserDto response = userService.createNewUser(inputData);

		verify(userPasswordEncoder).encode(inputData.getPassword());
		verify(userAdapter, times(2)).toUser(inputData);
		verify(userRepository).save(userToBeCreated);
	}


	@Test(expected = DuplicateUserException.class)
	public void shouldThrowExceptionWhenDuplicatedUserisCreated(){

		ArgumentCaptor<NewUserRequestDto> inputCaptor = ArgumentCaptor.forClass(NewUserRequestDto.class);

		String encodedPassword = "encodedPassword";
		User userToBeCreated = UserDataSupplier.generateRandomUser();

		// Initial data
		User userCreated = UserDataSupplier.generateRandomUserWithId();

		// Input data
		NewUserRequestDto inputData = new NewUserRequestDto("aa", "bb", "cc", "dd");
		//userToBeCreated.setPassword(inputData.getPassword());

		when(userPasswordEncoder.encode(anyString())).thenReturn(encodedPassword);
		//when(userAdapter.toUser(inputData)).thenReturn(userToBeCreated);
		when(userRepository.save(userToBeCreated)).thenReturn(userCreated);

		doThrow(new DuplicateUserException("aa")).when(userValidator).validate(userToBeCreated);


		// Make the actual call
		UserDto response = userService.createNewUser(inputData);

		verify(userAdapter).toUser(inputCaptor.capture());

		NewUserRequestDto paramPassedToCall = inputCaptor.getValue();



	}
}
