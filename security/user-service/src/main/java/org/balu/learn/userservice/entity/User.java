package org.balu.learn.userservice.entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.balu.learn.common.entity.BusinessObject;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="umt_user")
@Where(clause="is_active=1")
@Setter
@Getter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class User implements BusinessObject{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true, length=25)
	private String username;	
	
	@Column(nullable=false)
	private String password;
	
	@Column(length=25)
	private String displayName;
	
	@Column(name="is_active", nullable=false)
	private boolean active;
	
	@ManyToMany
	@JoinTable(
            name="umt_user_role_map",
            joinColumns = @JoinColumn( name="user_id"),
            inverseJoinColumns = @JoinColumn( name="role_id"))
	private List<Role> roles;
	
	@OneToOne(mappedBy="user", fetch=FetchType.LAZY)
	private UserProfile userProfile;
}
