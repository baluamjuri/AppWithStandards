package org.balu.learn.security.auth.config;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * To provide your custom authentication logic in {@link DaoAuthenticationProvider}.{@link #authenticate(Authentication)}
 * @author amjuribv
 *
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {	
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		return super.authenticate(auth);
	}	
	
}
