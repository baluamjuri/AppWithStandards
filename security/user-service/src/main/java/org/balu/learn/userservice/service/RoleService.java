package org.balu.learn.userservice.service;

import java.util.List;

import org.balu.learn.userservice.dto.PermissionResponseDTO;
import org.balu.learn.userservice.dto.RoleCreateRequestDTO;
import org.balu.learn.userservice.dto.RoleResponseDTO;
import org.balu.learn.userservice.dto.RoleUpdateRequestDTO;

public interface RoleService {
	/**
	 * Fetches all active roles
	 * 
	 * @return List of active roles
	 */
	List<RoleResponseDTO> findAll();

	/**
	 * Fetches role by ID
	 * 
	 * @param id
	 *            - ID of the active role
	 * @return {@link RoleResponseDTO}
	 */
	RoleResponseDTO findById(long id);

	/**
	 * Creates a role
	 * 
	 * @param roleDTO
	 * @return ID of the created role
	 */
	Long save(RoleCreateRequestDTO roleDTO);

	/**
	 * Partially updates the role
	 * 
	 * @param id
	 *            - ID of the Role
	 * @param roleDTO
	 * @return {@link RoleUpdateRequestDTO}
	 */
	RoleResponseDTO update(long id, RoleUpdateRequestDTO roleDTO);

	/**
	 * Soft deletes the role
	 * 
	 * @param id
	 *            - ID of the active role
	 * @return {@code true} if deleted else {@code fail}
	 */
	boolean delete(long id);

	/**
	 * Fetches the permissions that are assigned to the given role
	 * 
	 * @param id
	 *            - ID of the role
	 * @return list of permissions
	 */
	List<PermissionResponseDTO> findPermissionsByRoleId(long id);

	/**
	 * Assigns a permission to the role
	 * 
	 * @param roleId
	 *            - ID of the role
	 * @param permissionId
	 *            - ID of the permission
	 * @return {@code true} if assigns else {@code false}
	 */
	boolean addPermissionToRole(long roleId, long permissionId);

	/**
	 * Revokes the permission from the role
	 * 
	 * @param roleId
	 *            - ID of the role
	 * @param permissionId
	 *            - ID of the permission
	 * @return {@code true} if revoked successfully else {@code false}
	 */
	boolean deletePermissionFromRole(long roleId, long permissionId);
}