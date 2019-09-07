package org.balu.learn.userservice.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.userservice.validation.constants.ValidationConstants;
import org.balu.learn.userservice.validation.validator.ChangePasswordValidator;

/**
 * This is a method level annotation. It checks the credentials before update
 * the new password. {@code null} elements are considered valid.
 * 
 * @author amjuribv
 * @see ChangePasswordValidator
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChangePasswordValidator.class)
public @interface ChangePasswordValidation {
	String message() default ValidationConstants.CREDENTIALS_MSG;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
