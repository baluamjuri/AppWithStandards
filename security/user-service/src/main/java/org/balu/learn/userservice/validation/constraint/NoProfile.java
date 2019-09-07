package org.balu.learn.userservice.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.userservice.validation.constants.ValidationConstants;
import org.balu.learn.userservice.validation.validator.NoProfileValidator;

/**
 * The annotated element must be a persistent user which has no profile created
 * yet. {@code null} elements are considered valid.
 * 
 * @author amjuribv
 * @see NoProfileValidator
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoProfileValidator.class)
public @interface NoProfile {
	String message() default ValidationConstants.NOPROFILE_MSG;
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
