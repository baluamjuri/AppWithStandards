package org.balu.learn.security.auth.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="umt_role")
@Where(clause="is_active=1")
@Getter
@Setter
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="role_name", nullable=false, unique=true)
	private String name;
	
	@Column(name = "is_active")
	private boolean acitive;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
            name="umt_role_permission_map",
            joinColumns = @JoinColumn( name="role_id"),
            inverseJoinColumns = @JoinColumn( name="permission_id"))
	private List<Permission> permisssions;
}
