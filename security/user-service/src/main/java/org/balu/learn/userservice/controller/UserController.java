package org.balu.learn.userservice.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.balu.learn.common.validation.constraint.Existed;
import org.balu.learn.common.validation.constraint.ExistedId;
import org.balu.learn.common.validation.util.Sequence;
import org.balu.learn.common.validation.util.ValidationSequence;
import org.balu.learn.userservice.dto.ChangePasswordRequestDTO;
import org.balu.learn.userservice.dto.ProfileCreateRequestDTO;
import org.balu.learn.userservice.dto.ProfileUpdateRequestDTO;
import org.balu.learn.userservice.dto.RoleResponseDTO;
import org.balu.learn.userservice.dto.SignUpForm;
import org.balu.learn.userservice.dto.UserResponseDTO;
import org.balu.learn.userservice.entity.Role;
import org.balu.learn.userservice.entity.User;
import org.balu.learn.userservice.entity.UserProfile;
import org.balu.learn.userservice.service.UserService;
import org.balu.learn.userservice.validation.constraint.AssignRoleToUserValidation;
import org.balu.learn.userservice.validation.constraint.ChangePasswordValidation;
import org.balu.learn.userservice.validation.constraint.DeleteRoleFromUserValidation;
import org.balu.learn.userservice.validation.constraint.NoProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for {@link User} related operations
 * @author amjuribv
 *
 */
@RestController
@RequestMapping("users")
@Validated(ValidationSequence.class)	//To perform validations in sequence
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/** 
	 * Fetches the active user based on Id
	 * @param id - Should be an existing {@link User} ID
	 * @return {@link UserResponseDTO}
	 */
	@GetMapping("{id}")
	@PreAuthorize("hasRole('USER_READ')")
	public ResponseEntity<UserResponseDTO> findUserDetails(
			@PathVariable("id") 
			@ExistedId(User.class) Long id) {
		UserResponseDTO userDTO = userService.findUserDetails(id);
		return ResponseEntity.ok(userDTO);
	}
	
	/**
	 * Fetches the active user based on username
	 * @param name - Should be an existing username of {@link User}
	 * @return {@link UserResponseDTO}
	 */
	@GetMapping("name/{name}")
	@PreAuthorize("hasRole('USER_READ')")
	public ResponseEntity<UserResponseDTO> findUserDetails(
			@PathVariable("name") 
			@Existed(entityClass=User.class, property="username") String name ){
		UserResponseDTO userDTO = userService.findUserDetails(name);
		return ResponseEntity.ok(userDTO);
	}
	
	/**
	 * Fetch all the active users
	 * @return List of {@link UserResponseDTO}
	 */
	@GetMapping
	@PreAuthorize("hasRole('USER_READ')")
	public ResponseEntity<List<UserResponseDTO>> findAll(){
		List<UserResponseDTO> userDTOs = userService.findAll();

		//If there are no active users in the database
		if(userDTOs.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(userDTOs);		
	}
	
	/**
	 * To register a user in the database.
	 * @param signUpDTO
	 * @return {@link HttpStatus}.CREATED
	 */
	@PostMapping
	@PreAuthorize("hasRole('USER_CREATE')")
	public ResponseEntity<Void> save(@Valid @RequestBody SignUpForm signUpDTO){
		userService.save(signUpDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * To create the profile for a registered user.
	 * @param userId - Existing {@link User} ID that has no {@link UserProfile} created yet
	 * @param profileDTO
	 * @return {@link HttpStatus}.CREATED
	 */
	@PostMapping("{userId}/profile")
	@PreAuthorize("hasRole('USER_CREATE')")
	public ResponseEntity<Void> saveProfile(
			@PathVariable("userId")			
			@ExistedId(value=User.class, groups=Sequence.Step1.class)  
			@NoProfile(groups=Sequence.Step2.class) Long userId, 
			@Valid @RequestBody ProfileCreateRequestDTO profileDTO){
		userService.saveProfile(userId, profileDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	/**
	 * To update the existing profile
	 * @param id - Existing {@link User} ID
	 * @param profileDTO
	 * @return {@link HttpStatus}.OK
	 */
	@PatchMapping("{id}")
	@PreAuthorize("hasRole('USER_UPDATE')")
	public ResponseEntity<Void> update(
			@PathVariable("id") @Existed(entityClass=UserProfile.class, property = "user") Long id,
			@Valid @RequestBody ProfileUpdateRequestDTO profileDTO){
		userService.update(id, profileDTO);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Updates the password of logged in user
	 * @param principal - Logged in user details
	 * @param changePasswordDTO 
	 * @return {@link HttpStatus}.OK
	 */
	@PutMapping("changepassword")
	@PreAuthorize("hasRole('PASSWORD_UPDATE')")
	@ChangePasswordValidation
	public ResponseEntity<Void> changePassword(
			Principal principal,
			@Valid @RequestBody ChangePasswordRequestDTO changePasswordDTO) {
		userService.changePassword(principal.getName(), changePasswordDTO);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Deactivates the user
	 * @param id - ID of the user
	 * @return {@link HttpStatus}.OK
	 */
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('USER_DELETE')")
	public ResponseEntity<Void> delete(@PathVariable("id") @ExistedId(User.class) Long id) {
		userService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Gets the roles of the particular user
	 * @param id - ID of the user
	 * @return {@link HttpStatus}.NO_CONTENT if no roles assigned else {@link HttpStatus}.OK
	 */
	@GetMapping("{id}/roles")
	@PreAuthorize("hasRole('USER_ROLES_MAPPING')")
	public ResponseEntity<List<RoleResponseDTO>> getRolesByUserId(@PathVariable("id") @ExistedId(User.class) Long id) {
		List<RoleResponseDTO> roleDTOs = userService.findRolesByUserId(id);
		if (roleDTOs.isEmpty())
			return ResponseEntity.noContent().build();// 204
		else
			return ResponseEntity.ok(roleDTOs);
	}
	
	
	/**
	 * Assigns a role to the given user
	 * 
	 * @param userId - ID of the user
	 * @param roleId - ID of the role
	 * @return {@link HttpStatus}.OK
	 */
	@PutMapping("{userId}/roles/{roleId}")
	@PreAuthorize("hasRole('USER_ROLES_MAPPING')")
	@AssignRoleToUserValidation
	public ResponseEntity<Void> addRoleToUser(
			@PathVariable @ExistedId(User.class) Long userId,
			@PathVariable @ExistedId(Role.class) Long roleId) {
		userService.addRoleToUser(userId, roleId);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Revokes the role from the given user
	 * 
	 * @param userId - ID of the user
	 * @param roleId - ID of the role
	 * @return {@link HttpStatus}.OK
	 */
	@DeleteMapping("{userId}/roles/{roleId}")
	@PreAuthorize("hasRole('USER_ROLES_MAPPING')")
	@DeleteRoleFromUserValidation
	public ResponseEntity<Void> deleteRoleFromUser(
			@PathVariable @ExistedId(User.class) Long userId,
			@PathVariable @ExistedId(Role.class) Long roleId) {
		userService.deleteRoleFromUser(userId, roleId);
		return ResponseEntity.ok().build();
	}
}