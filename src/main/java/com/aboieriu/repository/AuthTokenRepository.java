package com.aboieriu.repository;

import com.aboieriu.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * @author aboieriu
 */
@org.springframework.stereotype.Repository
public interface AuthTokenRepository extends CrudRepository<AuthToken, String>,JpaRepository<AuthToken, String>, Repository<AuthToken, String> {
}
