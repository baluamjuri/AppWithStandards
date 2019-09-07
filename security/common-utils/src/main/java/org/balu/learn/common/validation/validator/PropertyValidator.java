package org.balu.learn.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.balu.learn.common.entity.BusinessObject;
import org.balu.learn.common.validation.constraint.Existed;
import org.balu.learn.common.validation.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks the particular column value existed in the table.
 * @author amjuribv
 * @see Existed
 */
public class PropertyValidator implements ConstraintValidator<Existed, Object> {
	@Autowired
	private ValidationRepository validationService;
	private Class<? extends BusinessObject> entityClass;
	private String property;

	@Override
	public void initialize(Existed constraintAnnotation) {
		entityClass = constraintAnnotation.entityClass();
		property = constraintAnnotation.property();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if(value==null)
			return true;
		return validationService.exists(entityClass, property, value);
	}

}
