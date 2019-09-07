package org.balu.learn.security.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="umt_permission")
@Where(clause="is_active=1")
@Getter
@Setter
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="permission_name", nullable=false, unique=true)
	private String name;
	
	@Column(name="is_active")
	private boolean active;
}
