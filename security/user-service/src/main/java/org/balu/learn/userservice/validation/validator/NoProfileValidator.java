package org.balu.learn.userservice.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.balu.learn.userservice.entity.User;
import org.balu.learn.userservice.repository.UserRepository;
import org.balu.learn.userservice.validation.constraint.NoProfile;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Checks the persistent user that has no profile. Returns false if profile exists. 
 * @author amjuribv
 * @see NoProfile
 */
public class NoProfileValidator implements ConstraintValidator<NoProfile, Long> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		if(value==null)
			return true;
		User user = userRepository.findById(value).get();
		return user.getUserProfile()==null;
	}

}
