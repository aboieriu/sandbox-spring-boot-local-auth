package com.aboieriu.service;

import com.aboieriu.entity.AuthToken;
import com.aboieriu.entity.User;

/**
 * @author aboieriu
 */
public interface AuthTokenService {
	AuthToken createOrGetTokenForUser(User user);
}
