package com.aboieriu.service;

import com.aboieriu.entity.AuthToken;
import com.aboieriu.entity.User;
import com.aboieriu.repository.AuthTokenRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

/**
 * @author aboieriu
 */
@Component
public class AuthTokenServiceImpl implements AuthTokenService {

	private static final int EXPIRY_PERIOD_VALUE = 5;
	private static final int EXPIRY_PERIOD_UNIT = Calendar.DAY_OF_MONTH;

	@Resource
	private AuthTokenRepository authTokenRepository;

	@Override
	public AuthToken createOrGetTokenForUser(User user) {
		Optional<AuthToken> authTokenOptional = user.getActiveAuthToken();
		if (authTokenOptional.isPresent()) {
			AuthToken token = authTokenOptional.get();
			if (!verifyExpired(token)) {
				return token;
			} else {
				// Disable the token if it's already expired
				token.setActive(false);
				authTokenRepository.save(token);
			}
		}
		return generateNewTokenForUser(user);
	}

	private boolean verifyExpired(AuthToken authToken) {
		Calendar now = Calendar.getInstance();
		Calendar expiry = Calendar.getInstance();
		expiry.setTime(authToken.getExpiry());

		return now.compareTo(expiry) > 0;
	}

	private AuthToken generateNewTokenForUser(User user) {
		Calendar expiry = Calendar.getInstance();
		expiry.add(EXPIRY_PERIOD_UNIT, EXPIRY_PERIOD_VALUE);

		AuthToken authToken = new AuthToken();
		authToken.setExpiry(expiry.getTime());
		authToken.setUser(user);
		authToken.setActive(true);
		authToken.setToken(UUID.randomUUID().toString());
		user.setToken(authToken);

		authTokenRepository.save(authToken);

		return authToken;
	}
}
