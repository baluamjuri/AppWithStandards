package org.balu.learn.userservice.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.userservice.validation.constants.ValidationConstants;
import org.balu.learn.userservice.validation.validator.DeleteRoleFromUserValidator;

/**
 * Checks whether the mapping between user and role is already existed or not.
 * Validation fails if not existed. The first parameter should be userId and
 * second parameter should be roleId and those should not be null.
 * 
 * @author amjuribv
 *
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DeleteRoleFromUserValidator.class)
public @interface DeleteRoleFromUserValidation {
	String message() default ValidationConstants.ROLE_NOT_YET_ASSIGNED_MSG;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
