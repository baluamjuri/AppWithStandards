package org.balu.learn.userservice.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.userservice.validation.constants.ValidationConstants;
import org.balu.learn.userservice.validation.validator.AddPermissionToRoleValidator;

/**
 * Checks whether the mapping between role and permission is already existed or not. Validation fails if
 * existed. The first parameter should be roleId and second parameter should be
 * permissionId and those should not be null.
 * 
 * @author amjuribv
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddPermissionToRoleValidator.class) 
public @interface AddPermissionToRoleValidation {
	String message() default ValidationConstants.PERMISSION_ASSIGNED_ALREADY_MSG;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
