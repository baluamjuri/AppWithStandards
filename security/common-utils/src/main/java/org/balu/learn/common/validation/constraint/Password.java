package org.balu.learn.common.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.common.validation.constants.CommonValidationConstants;
import org.balu.learn.common.validation.validator.PasswordValidator;

/**
 * The annotated element must satisfies the password pattern
 * {@code ValidationConstants.PASSWORD}. {@code null} elements are considered
 * valid.
 * 
 * @author amjuribv
 * @see PasswordValidator
 * @see CommonValidationConstants
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
	String message() default CommonValidationConstants.PASSWORD_MSG;
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
