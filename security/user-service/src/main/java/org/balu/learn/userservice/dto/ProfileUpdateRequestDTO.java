package org.balu.learn.userservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.common.validation.util.Sequence.Step1;
import org.balu.learn.userservice.entity.UserProfile;
import org.balu.learn.userservice.validation.constants.ValidationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileUpdateRequestDTO {
	@Pattern(regexp=ValidationConstants.DISPLAYNAME, message=ValidationConstants.DISPLAYNAME_MSG)
	private String displayName;
	
	@Email(regexp=org.balu.learn.common.validation.constants.CommonValidationConstants.EMAIL)
	@Unique(entityClass = UserProfile.class, property = "email", groups=Step1.class)
	private String email;

	@Size(min=10, max=10, message=ValidationConstants.MOBILE_MSG)
	private String mobile;
}
