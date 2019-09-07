package org.balu.learn.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.balu.learn.common.entity.BusinessObject;
import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.common.validation.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks to satisfy unique constraint. returns true if the value does not
 * exist.
 * 
 * @author amjuribv
 * @see Unique
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {
	@Autowired
	private ValidationRepository validationService;
	private Class<? extends BusinessObject> entityClass;
	private String property;

	@Override
	public void initialize(Unique constraintAnnotation) {
		entityClass = constraintAnnotation.entityClass();
		property = constraintAnnotation.property();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		if (value instanceof String && ((String) value).trim().length() == 0)
			return false;
		return !validationService.exists(entityClass, property, value);
	}

}
