package org.balu.learn.common.transformers;

import java.util.List;
import java.util.stream.Collectors;

import org.balu.learn.common.dto.BeanValidationErrorResponseDTO;
import org.springframework.validation.BindingResult;

/**
 * Converter for validation errors.
 * @author amjuribv
 *
 */
public class ValidationErrorTransformer {
	
	/**
	 * Converts the {@link BindingResult} to list of {@link BeanValidationErrorResponseDTO}s
	 * @param result
	 * @return list of {@link BeanValidationErrorResponseDTO}s
	 */
	public static List<BeanValidationErrorResponseDTO> getAllErrorDetails(BindingResult result){
		return result.getFieldErrors()
					.stream()
					.map(error -> new BeanValidationErrorResponseDTO(error))
					.collect(Collectors.toList());	
	}
}
