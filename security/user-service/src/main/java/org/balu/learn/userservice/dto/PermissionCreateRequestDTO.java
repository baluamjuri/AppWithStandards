package org.balu.learn.userservice.dto;

import javax.validation.constraints.NotBlank;

import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.userservice.entity.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionCreateRequestDTO {
	
	@NotBlank	
	@Unique(entityClass = Permission.class, property = "name")
	private String name;
	
	private String description;
}
