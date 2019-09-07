package org.balu.learn.common.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParameterConstraintViolationResponseDTO {
	private String path;
	
	//Key is path variable or request parameter's value and value is message
	private Map<Object, String> violationMap; 	
}
