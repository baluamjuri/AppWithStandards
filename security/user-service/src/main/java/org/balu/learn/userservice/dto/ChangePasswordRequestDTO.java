package org.balu.learn.userservice.dto;

import javax.validation.constraints.NotBlank;

import org.balu.learn.common.validation.constraint.Password;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequestDTO {
	
	@NotBlank
	private String oldPassword;
	
	@NotBlank
	@Password
	private String newPassword;
}
