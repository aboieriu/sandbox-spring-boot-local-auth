package com.aboieriu.repository;

import com.aboieriu.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author aboieriu
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

	@Resource
	private UserRepository userRepository;

	@Test
	public void createNewUser(){
		User user = UserDataSupplier.generateRandomUser();
		userRepository.save(user);
		userRepository.flush();
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotCreateUserWithNullUsername(){
		User user = UserDataSupplier.generateRandomUser();

		user.setUsername(null);

		userRepository.save(user);
		userRepository.flush();
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldNotCreateUserWithNullPassword(){
		User user = UserDataSupplier.generateRandomUser();

		user.setPassword(null);

		userRepository.save(user);
		userRepository.flush();
	}
}
