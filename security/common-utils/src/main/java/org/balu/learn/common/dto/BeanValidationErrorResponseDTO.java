package org.balu.learn.common.dto;

import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BeanValidationErrorResponseDTO {
	private String errorMessage;
	private String objectName;
	private String field;
	private Object rejectedValue;
	private String code;
	
	public BeanValidationErrorResponseDTO(FieldError fieldError){
		this.errorMessage = fieldError.getDefaultMessage();
		this.objectName = fieldError.getObjectName();
		this.field = fieldError.getField();
		this.rejectedValue = fieldError.getRejectedValue();
		this.code = fieldError.getCode();
	}
}
