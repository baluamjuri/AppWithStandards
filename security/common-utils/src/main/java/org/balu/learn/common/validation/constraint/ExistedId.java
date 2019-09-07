package org.balu.learn.common.validation.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.balu.learn.common.entity.BusinessObject;
import org.balu.learn.common.validation.constants.CommonValidationConstants;
import org.balu.learn.common.validation.validator.IdValidator;

/**
 * The annotated element must be a persisted ID of provided entity. {@code null}
 * elements are considered valid. Accepts {@code Long}
 * 
 * @author amjuribv
 * @see IdValidator
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdValidator.class)
public @interface ExistedId {
	String message() default CommonValidationConstants.EXISTED_ID_MSG;
	Class<? extends BusinessObject> value();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
