package org.balu.learn.userservice.service;

import java.util.List;

import org.balu.learn.userservice.dto.ChangePasswordRequestDTO;
import org.balu.learn.userservice.dto.ProfileCreateRequestDTO;
import org.balu.learn.userservice.dto.ProfileUpdateRequestDTO;
import org.balu.learn.userservice.dto.RoleResponseDTO;
import org.balu.learn.userservice.dto.SignUpForm;
import org.balu.learn.userservice.dto.UserResponseDTO;

public interface UserService {
	/**
	 * 
	 * @param username
	 * @return
	 */
	UserResponseDTO findUserDetails(String username);
	UserResponseDTO findUserDetails(long id) ;
	Long save(SignUpForm signUpDTO);
	void update(long id, ProfileUpdateRequestDTO userDTO);
	boolean delete(long id);
	public List<UserResponseDTO> findAll();
	void changePassword(String username, ChangePasswordRequestDTO changePasswordDTO); 
	List<RoleResponseDTO> findRolesByUserId(long id);
	boolean addRoleToUser(long userId, long roleId);
	boolean deleteRoleFromUser(long userId, long roleId);
	Long saveProfile(Long userId, ProfileCreateRequestDTO profileDTO);
}