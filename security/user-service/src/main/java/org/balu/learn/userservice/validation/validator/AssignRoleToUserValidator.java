package org.balu.learn.userservice.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import org.balu.learn.userservice.repository.UserRepository;
import org.balu.learn.userservice.validation.constraint.AssignRoleToUserValidation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks whether the mapping between user and role is already existed or not. Validation fails if
 * existed. The first parameter should be userId and second parameter should be
 * roleId and those should not be null.
 * 
 * @author amjuribv
 *
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class AssignRoleToUserValidator implements ConstraintValidator<AssignRoleToUserValidation, Object[]> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		Long userId = (Long) value[0];
		Long roleId = (Long) value[1];
		return !userRepository.existsByIdAndRoles_Id(userId, roleId);
	}

}
