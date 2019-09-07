package org.balu.learn.security.auth.config;

import javax.sql.DataSource;

import org.balu.learn.security.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Configuration for Authorization service
 * 
 * @author amjuribv
 *
 */
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
    private AuthenticationManager authenticationManager;	
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserService userService;
	
	@Override
    public void configure(ClientDetailsServiceConfigurer clients)throws Exception {
		//Clients are registered in database
		clients.jdbc(dataSource);
    }	
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception {
        endpoints.tokenStore(tokenStore())
          		.authenticationManager(authenticationManager)
          		.userDetailsService(userService);
    }
	
	@Bean
    public TokenStore tokenStore() {
		//Using JDBC token store
		return new JdbcTokenStore(dataSource);
    }

	/**
	 * This bean is to use the default logout(revoking token from database) service
	 * @return {@link DefaultTokenServices}
	 */
	@Bean
	public DefaultTokenServices defaultTokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
	    return defaultTokenServices;
	}
	
	
	@Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) 
      throws Exception {
        oauthServer
        	.tokenKeyAccess("permitAll()") // Any user can access path: /oauth/token
        	.checkTokenAccess("isAuthenticated()"); // Every authenticated user allowed on path: /oauth/check_token 
    }
}
