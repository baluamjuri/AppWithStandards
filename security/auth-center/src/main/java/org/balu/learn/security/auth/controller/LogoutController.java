package org.balu.learn.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Logout service
 * @author amjuribv
 *
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {
 
	@Autowired
	private DefaultTokenServices defaultTokenServices;
	
	/**
	 * Logout service 
	 * @param token - should be an oauth token either with bearer prefix or not. 'bearer' prefix here ignores case
	 * @return {@code OK} status
	 * @throws InvalidClientException
	 */
    @PostMapping
    public ResponseEntity<Void> create(@RequestHeader("Authorization") String token) throws InvalidClientException {
    	if(StringUtils.startsWithIgnoreCase(token, OAuth2AccessToken.BEARER_TYPE)) {
    		token = token.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
    	}
		defaultTokenServices.revokeToken(token);
		return ResponseEntity.ok().build();			
	}
}