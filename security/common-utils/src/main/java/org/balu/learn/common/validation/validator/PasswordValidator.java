package org.balu.learn.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.balu.learn.common.validation.constants.CommonValidationConstants;
import org.balu.learn.common.validation.constraint.Password;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

/**
 * Validates the password pattern
 * 
 * @author amjuribv
 *
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Value(CommonValidationConstants.PASSWORD)
	private String regExp;

	@Override
	public boolean isValid(@Nullable String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		return value.matches(regExp);
	}

}
