package org.balu.learn.userservice.validation.validator;

import java.security.Principal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import org.balu.learn.userservice.dto.ChangePasswordRequestDTO;
import org.balu.learn.userservice.entity.User;
import org.balu.learn.userservice.repository.UserRepository;
import org.balu.learn.userservice.validation.constraint.ChangePasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Checks the credentials before update the new password. First parameter should
 * be {@link Principal} and second should be {@link ChangePasswordRequestDTO}
 * 
 * @author amjuribv
 * @see ChangePasswordValidation
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ChangePasswordValidator implements ConstraintValidator<ChangePasswordValidation, Object[]> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		if (value[0] == null || value[1] == null)
			return true;
		Principal principal = (Principal) value[0];
		ChangePasswordRequestDTO changePasswordDTO = (ChangePasswordRequestDTO) value[1];
		String username = principal.getName();
		User user = userRepository.findByUsername(username).get();
		boolean isMatched = passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword());
		return isMatched;
	}

}
