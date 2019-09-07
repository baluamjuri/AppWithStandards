package org.balu.learn.userservice.dto;

import javax.validation.constraints.NotBlank;

import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.userservice.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleCreateRequestDTO {
	@NotBlank	
	@Unique(entityClass = Role.class, property = "name")
	private String name;
	private String description;
}
