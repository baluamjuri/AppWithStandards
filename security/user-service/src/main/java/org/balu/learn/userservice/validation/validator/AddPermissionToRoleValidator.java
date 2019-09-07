package org.balu.learn.userservice.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import org.balu.learn.userservice.repository.RoleRepository;
import org.balu.learn.userservice.validation.constraint.AddPermissionToRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks whether the combination is already existed or not. Validation fails if
 * existed. The first parameter should be roleId and second parameter should be
 * permissionId and those should not be null.
 * 
 * @author amjuribv
 *
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class AddPermissionToRoleValidator implements ConstraintValidator<AddPermissionToRoleValidation, Object[]> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		Long roleId = (Long) value[0];
		Long permissionId = (Long) value[1];
		return !roleRepository.existsByIdAndPermissions_Id(roleId, permissionId);
	}

}
