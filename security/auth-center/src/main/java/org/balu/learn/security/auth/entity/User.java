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

import org.balu.learn.common.entity.BusinessObject;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="umt_user")
@Where(clause="is_active=1")
@Getter
@Setter
public class User implements BusinessObject{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(nullable=false)
	private String username;

	@Column(nullable=false)
	private String password;
	
	@Column(name="is_active", nullable=false)
	private boolean active;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
            name="umt_user_role_map",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="role_id"))
	private List<Role> roles;
}
