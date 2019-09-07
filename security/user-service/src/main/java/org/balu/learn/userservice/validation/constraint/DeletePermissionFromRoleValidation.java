package org.balu.learn.userservice.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.userservice.validation.constants.ValidationConstants;
import org.balu.learn.userservice.validation.validator.DeletePermissionFromRoleValidator;


/**
 * Checks whether the mapping between role and permission is already existed or not. Validation fails if
 * not existed. The first parameter should be roleId and second parameter should be
 * permissionId and those should not be null.
 * 
 * @author amjuribv
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DeletePermissionFromRoleValidator.class)
public @interface DeletePermissionFromRoleValidation {
	String message() default ValidationConstants.PERMISSION_NOT_YET_ASSIGNED_MSG;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
