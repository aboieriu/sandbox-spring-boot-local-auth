package com.aboieriu.service;

import com.aboieriu.domain.AuthResponseDto;
import com.aboieriu.domain.UserAuthRequestDto;

/**
 * @author aboieriu
 */
public interface AuthenticationService {
	AuthResponseDto authenticate(UserAuthRequestDto userAuthRequestDto);
}
