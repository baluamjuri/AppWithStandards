package org.balu.learn.userservice.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import org.balu.learn.userservice.repository.RoleRepository;
import org.balu.learn.userservice.validation.constraint.DeletePermissionFromRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* Checks whether the combination is already existed or not. Validation fails if not existed
* 
* @author amjuribv
*
*/
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class DeletePermissionFromRoleValidator implements ConstraintValidator<DeletePermissionFromRoleValidation, Object[]> {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean isValid(Object[] value, ConstraintValidatorContext context) {
		Long roleId = (Long) value[0];
		Long permissionId = (Long) value[1];
		return roleRepository.existsByIdAndPermissions_Id(roleId, permissionId);
	}

}
