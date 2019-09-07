package org.balu.learn.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.balu.learn.common.validation.constraint.ExistedId;
import org.balu.learn.userservice.dto.PermissionResponseDTO;
import org.balu.learn.userservice.dto.RoleCreateRequestDTO;
import org.balu.learn.userservice.dto.RoleResponseDTO;
import org.balu.learn.userservice.dto.RoleUpdateRequestDTO;
import org.balu.learn.userservice.entity.Permission;
import org.balu.learn.userservice.entity.Role;
import org.balu.learn.userservice.service.RoleService;
import org.balu.learn.userservice.validation.constraint.AddPermissionToRoleValidation;
import org.balu.learn.userservice.validation.constraint.DeletePermissionFromRoleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest endpoints for {@link Role}
 * 
 * @author amjuribv
 *
 */
@RestController
@RequestMapping("roles")
@Validated
public class RoleController {

	@Autowired
	private RoleService roleService;

	/**
	 * Get the role by ID
	 * 
	 * @param id
	 *            - Existing role ID
	 * @return {@code OK} status with respective role
	 */
	@GetMapping("{id}")
	@PreAuthorize("hasRole('ROLES_READ')")
	public ResponseEntity<RoleResponseDTO> findById(@PathVariable @ExistedId(Role.class) Long id) {
		RoleResponseDTO roleDTO = roleService.findById(id);
		return ResponseEntity.ok(roleDTO);
	}

	/**
	 * Get all the roles
	 * 
	 * @return {@code no-content} if there are no roles existed else {@code OK}
	 *         status with list of roles
	 */
	@GetMapping
	@PreAuthorize("hasRole('ROLES_READ')")
	public ResponseEntity<List<RoleResponseDTO>> findAll() {
		List<RoleResponseDTO> roleDTOs = roleService.findAll();

		// If there are no active roles in the database
		if (roleDTOs.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(roleDTOs);
	}

	/**
	 * Create a role and returns {@code Created} status.
	 * 
	 * @param roleDTO
	 */
	@PostMapping
	@PreAuthorize("hasRole('ROLES_CREATE')")
	public ResponseEntity<Void> save(@Valid @RequestBody RoleCreateRequestDTO roleDTO) {
		roleService.save(roleDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Updates the existing role
	 * 
	 * @param id
	 *            - Existing role ID
	 * @param roleDTO
	 * @return {@code OK} status after updation
	 */
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ROLES_UPDATE')")
	public ResponseEntity<Void> update(@PathVariable("id") @ExistedId(Role.class) Long id,
			@Valid @RequestBody RoleUpdateRequestDTO roleDTO) {
		roleService.update(id, roleDTO);
		return ResponseEntity.ok().build();
	}

	/**
	 * Deletes role and {@code OK} status
	 * 
	 * @param id
	 *            - Exisitng role ID
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLES_DELETE')")
	public ResponseEntity<Void> delete(@PathVariable @ExistedId(Role.class) Long id) {
		roleService.delete(id);
		return ResponseEntity.ok().build();
	}

	/**
	 * Get the list of permissions by role ID
	 * 
	 * @param roleId
	 *            - existing role Id
	 * @return {@code No content} status if there are no permissions assigned to the
	 *         role else {@code OK} status with list of permissions
	 */
	@GetMapping("{roleId}/permissions")
	@PreAuthorize("hasRole('ROLES_PERMISSION_MAPPING')")
	public ResponseEntity<List<PermissionResponseDTO>> findPermissionsByRoleId(
			@PathVariable @ExistedId(Role.class) Long roleId) {
		List<PermissionResponseDTO> permissionDTOs = roleService.findPermissionsByRoleId(roleId);
		if (permissionDTOs.isEmpty())
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(permissionDTOs);
	}

	/**
	 * Assigns a permission to the given role
	 * 
	 * @param roleId
	 *            - Existing role ID
	 * @param permissionId
	 *            - Existing permission ID that is not already assigned to given
	 *            role
	 * @return {@code OK} status if success
	 */
	@PutMapping("{roleId}/permissions/{permissionId}")
	@PreAuthorize("hasRole('ROLES_PERMISSION_MAPPING')")
	@AddPermissionToRoleValidation
	public ResponseEntity<Void> addPermissionToRole(@PathVariable @ExistedId(Role.class) Long roleId,
			@PathVariable @ExistedId(Permission.class) Long permissionId) {
		roleService.addPermissionToRole(roleId, permissionId);
		return ResponseEntity.ok().build();
	}

	/**
	 * Revoking permission from role
	 * 
	 * @param roleId
	 *            - Existing role ID
	 * @param permissionId
	 *            - Existing permission ID that is already assigned to given role
	 * @return {@code OK} status if success
	 */
	@DeleteMapping("{roleId}/permissions/{permissionId}")
	@PreAuthorize("hasRole('ROLES_PERMISSION_MAPPING')")
	@DeletePermissionFromRoleValidation
	public ResponseEntity<Void> deletePermissionFromRole(@PathVariable @ExistedId(Role.class) Long roleId,
			@PathVariable @ExistedId(Permission.class) Long permissionId) {
		roleService.deletePermissionFromRole(roleId, permissionId);
		return ResponseEntity.ok().build();
	}
}
