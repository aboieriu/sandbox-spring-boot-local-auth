package com.aboieriu.repository;

import com.aboieriu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * @author aboieriu
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, String>,JpaRepository<User, String>, Repository<User, String> {
	Optional<User> findByUsername(String username);
}
