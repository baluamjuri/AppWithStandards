package org.balu.learn.userservice.dto;

import org.balu.learn.common.validation.constraint.Unique;
import org.balu.learn.userservice.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleUpdateRequestDTO {
	@Unique(entityClass = Role.class, property = "name")
	private String name;
	private String description;
}
