package org.balu.learn.common.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.common.entity.BusinessObject;
import org.balu.learn.common.validation.constants.CommonValidationConstants;
import org.balu.learn.common.validation.validator.UniqueValidator;

/**
 * The annotated element must be satisfies unique constraint of an entity i.e.
 * the value should not be existed already. {@code null} elements are considered
 * valid.
 * 
 * @author amjuribv
 * @see UniqueValidator
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {
	String message() default CommonValidationConstants.UNIQUE_MSG;
	Class<? extends BusinessObject> entityClass();
	String property();
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
