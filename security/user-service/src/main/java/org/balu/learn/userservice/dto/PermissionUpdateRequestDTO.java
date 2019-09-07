package org.balu.learn.userservice.dto;

import org.balu.learn.common.validation.constraint.Existed;
import org.balu.learn.userservice.entity.Permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionUpdateRequestDTO {
	@Existed(entityClass = Permission.class, property = "name")
	private String name;
	
	private String description;
}
