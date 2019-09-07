package org.balu.learn.userservice.dto;

import org.balu.learn.userservice.entity.Gender;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO {
	private Long id;
	
	private String displayName;
	
	private String email;

	private Gender gender;
	
	private String mobile;
}
