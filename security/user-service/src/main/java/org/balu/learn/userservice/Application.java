package org.balu.learn.userservice;

import javax.management.relation.Role;

import org.balu.learn.userservice.entity.Permission;
import org.balu.learn.userservice.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * This is User Management application which provides restful services for
 * {@link User}, {@link Role}, {@link Permission} CRUD.
 * 
 * <p>
 * The following features incorporated in this application.
 * <li>Spring boot</li>
 * <li>OAuth2 Resource server</li>
 * <li>Spring Method level security</li>
 * <li>Spring REST</li>
 * <li>Spring JPA - Query by method</li>
 * <li>JSR 303 validations + Spring validations - customized, cross field, cross
 * parameter</li>
 * <li>Lombok - logging, setter, getter etc,.</li>
 * <li>Model Mapper</li>
 * </p>
 * 
 * @author amjuribv
 *
 */

@SpringBootApplication(scanBasePackages="org.balu.learn")
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
