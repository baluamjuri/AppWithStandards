package org.balu.learn.userservice.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.balu.learn.common.entity.BusinessObject;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="umt_role")
@Where(clause="is_active=1")
@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class Role implements BusinessObject{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="role_name", nullable=false, unique=true)
	private String name;
	
	@Column(name = "is_active", nullable=false)
	private boolean active;
	private String description;
	
//	@ManyToMany(fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@ManyToMany
	@JoinTable(
            name="umt_role_permission_map",
            joinColumns = @JoinColumn( name="role_id"),
            inverseJoinColumns = @JoinColumn( name="permission_id"))
	private List<Permission> permissions;
}
