package org.balu.learn.userservice.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.balu.learn.common.validation.constraint.Password;
import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.common.validation.util.Sequence;
import org.balu.learn.userservice.entity.User;
import org.balu.learn.userservice.validation.constants.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

/**
 * User registration form 
 * @author amjuribv
 *
 */
@Getter
@Setter
public class SignUpForm {	
	@NotNull
	@Unique(entityClass=User.class, property="username", groups=Sequence.Step2.class)
	@Pattern(regexp = ValidationConstants.USERNAME, message=ValidationConstants.USERNAME_MSG)
	private String username;
	
	
	@NotNull
	@Password
	private String password;
	
	@NotNull
	@Pattern(regexp=ValidationConstants.DISPLAYNAME, message=ValidationConstants.DISPLAYNAME_MSG)
	private String displayName;
}
