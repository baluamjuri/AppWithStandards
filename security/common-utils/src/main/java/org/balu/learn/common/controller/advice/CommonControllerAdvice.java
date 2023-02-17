package org.balu.learn.common.controller.advice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.balu.learn.common.dto.BeanValidationErrorResponseDTO;
import org.balu.learn.common.dto.ParameterConstraintViolationResponseDTO;
import org.balu.learn.common.transformers.ValidationErrorTransformer;
import org.balu.learn.common.validation.constants.CommonValidationConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

/**
 * Global exception handling
 * 
 * @author amjuribv
 *
 */
@RestControllerAdvice
@ConditionalOnProperty("common.controller-advice.enable")
@Log4j2
public class CommonControllerAdvice {

	/**
	 * Handles bean validation errors
	 * 
	 * @param ex - To get the bean validation errors
	 * @return list of {@link BeanValidationErrorResponseDTO}s
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public List<BeanValidationErrorResponseDTO> handle(MethodArgumentNotValidException  ex){
		log.error(ex);
		List<BeanValidationErrorResponseDTO> errrorDetailsList = ValidationErrorTransformer.getAllErrorDetails(ex.getBindingResult());
		return errrorDetailsList;
	}
	
	/**
	 * Handles parameter constraint violations
	 * @param request
	 *            - to get the servlet path
	 * @param ex
	 *            - to get the constraint violations for request parameters, path
	 *            variables etc.
	 * @return {@link ParameterConstraintViolationResponseDTO}
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ParameterConstraintViolationResponseDTO handle(HttpServletRequest request, ConstraintViolationException ex){
		log.error(ex);
		Map<Object, String> violationMap =				
				ex.getConstraintViolations()
				.stream()
				.collect(
						Collectors.toMap(ConstraintViolation::getInvalidValue, 
								ConstraintViolation::getMessage,
								(msg1, msg2) -> msg1+", "+msg2));
		
		
		ParameterConstraintViolationResponseDTO parameterConstraintViolationResponseDTO = 
				ParameterConstraintViolationResponseDTO.builder()
				.violationMap(violationMap)
				.path(request.getServletPath())
				.build();
		log.info("Validations: {}", parameterConstraintViolationResponseDTO);
		return parameterConstraintViolationResponseDTO;
	}
	
	/**
	 * If it is a cross-parameter constraint then returns a string else returns
	 * parameter value
	 * 
	 * @param constraintViolation
	 * @return returns a string if cross-parameter constraint encountered else
	 *         normal parameter value
	 */
	private <T> Object getInvalidValue(ConstraintViolation<T> constraintViolation) {
		Object invalidValue = constraintViolation.getInvalidValue();
		if(invalidValue instanceof Object[])
			return CommonValidationConstants.CROSS_PARAMETERS_ERROR_KEY;
		return invalidValue;
	}
}
