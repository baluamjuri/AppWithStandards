package org.balu.learn.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.common.validation.util.Sequence;
import org.balu.learn.userservice.entity.Gender;
import org.balu.learn.userservice.entity.UserProfile;
import org.balu.learn.userservice.validation.constants.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileCreateRequestDTO {
	@Unique(entityClass=UserProfile.class, property = "email", groups=Sequence.Step1.class)
	@Email(regexp=org.balu.learn.common.validation.constants.CommonValidationConstants.EMAIL)
	private String email;

	@NotNull
	private Gender gender;
	
	@Size(min=10, max=10, message=ValidationConstants.MOBILE_MSG)
	private String mobile;
}
