package org.balu.learn.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.balu.learn.common.entity.BusinessObject;

import lombok.Getter;
import lombok.Setter;

/**
 * Profile of the user.
 * 
 * @author amjuribv
 *
 */
@Setter
@Getter
@Entity
@Table(name="umt_user_profile")
public class UserProfile implements BusinessObject{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private Gender gender;
	
	@Column(length=10)
	private String mobile;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
}
