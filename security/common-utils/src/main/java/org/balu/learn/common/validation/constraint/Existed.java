package org.balu.learn.common.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.common.entity.BusinessObject;
import org.balu.learn.common.validation.constants.CommonValidationConstants;
import org.balu.learn.common.validation.validator.PropertyValidator;

/**
 * The annotated element must be a persisted value of the provided entity and
 * column. {@code null} elements are considered valid.
 * 
 * @author amjuribv
 * @see PropertyValidator
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PropertyValidator.class)
public @interface Existed {
	String message() default CommonValidationConstants.EXISTED_MSG;
	Class<? extends BusinessObject> entityClass();
	String property();
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
