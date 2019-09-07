package org.balu.learn.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.balu.learn.common.entity.BusinessObject;
import org.balu.learn.common.validation.constraint.ExistedId;
import org.balu.learn.common.validation.repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Validates the ID of a persisted entity.
 * @author amjuribv
 * @see ExistedId
 */
public class IdValidator implements ConstraintValidator<ExistedId, Long> {

	@Autowired
	private ValidationRepository validationService;
	
	private Class<? extends BusinessObject> entityClass;
	
	@Override
	public void initialize(ExistedId constraintAnnotation) {
		entityClass = constraintAnnotation.value();		
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(value==null)
			return true;
		return validationService.existsById(entityClass, value);
	}

}
