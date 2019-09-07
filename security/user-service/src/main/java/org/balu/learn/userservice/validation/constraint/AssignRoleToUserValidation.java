package org.balu.learn.userservice.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.userservice.validation.constants.ValidationConstants;
import org.balu.learn.userservice.validation.validator.AssignRoleToUserValidator;

/**
 * Checks whether the mapping between user and role is already existed or not. Validation fails if
 * existed. The first parameter should be userId and second parameter should be
 * roleId and those should not be null.
 * 
 * @author amjuribv
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssignRoleToUserValidator.class)
public @interface AssignRoleToUserValidation {
	String message() default ValidationConstants.ROLE_ASSIGNED_ALREADY_MSG;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
