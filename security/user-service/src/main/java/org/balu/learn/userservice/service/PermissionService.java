package org.balu.learn.userservice.service;

import java.util.List;

import org.balu.learn.userservice.dto.PermissionCreateRequestDTO;
import org.balu.learn.userservice.dto.PermissionResponseDTO;
import org.balu.learn.userservice.dto.PermissionUpdateRequestDTO;

/**
 * 
 * @author amjuribv
 *
 */
public interface PermissionService {
	/**
	 * Fetches all permissions
	 * 
	 * @return List of permissions
	 */
	List<PermissionResponseDTO> findAll();

	/**
	 * Fetch permission based on ID
	 * 
	 * @param id
	 *            - ID of the permission
	 * @return {@link PermissionResponseDTO}
	 */
	PermissionResponseDTO findById(long id);

	/**
	 * Creates a permission
	 * 
	 * @param permissionDTO
	 * @return ID of the created permission
	 */
	Long save(PermissionCreateRequestDTO permissionDTO);

	/**
	 * Partially updates a permission
	 * 
	 * @param id
	 *            - ID of the permission to be update
	 * @param permissionDTO
	 */
	void update(long id, PermissionUpdateRequestDTO permissionDTO);

	/**
	 * Soft delete the permisssion
	 * 
	 * @param id
	 *            - ID of the permission
	 */
	void delete(long id);
}
