package org.balu.learn.userservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.balu.learn.common.validation.constraint.ExistedId;
import org.balu.learn.userservice.dto.PermissionCreateRequestDTO;
import org.balu.learn.userservice.dto.PermissionResponseDTO;
import org.balu.learn.userservice.dto.PermissionUpdateRequestDTO;
import org.balu.learn.userservice.entity.Permission;
import org.balu.learn.userservice.service.PermissionService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest endpoints for {@link Permission}
 * 
 * @author amjuribv
 *
 */
@RestController
@RequestMapping("permissions")
@Validated // For request parameters, path parameters validation
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	/**
	 * Get all the permissions
	 * 
	 * @return {@code no-content} if there are no permissions existed else
	 *         {@code OK} status with list of permissions
	 */
	@GetMapping
	@PreAuthorize("hasRole('PERMISSION_READ')")
	public ResponseEntity<List<PermissionResponseDTO>> findAll() {
		List<PermissionResponseDTO> permissionDTOs = permissionService.findAll();

		// If there are no active roles in the database
		if (permissionDTOs.isEmpty())
			ResponseEntity.noContent().build();
		return ResponseEntity.ok(permissionDTOs);
	}

	/**
	 * Get the permission by ID
	 * 
	 * @param id
	 *            - Existing permission ID
	 * @return {@code OK} status with respective permission
	 */
	@GetMapping("{id}")
	@PreAuthorize("hasRole('PERMISSION_READ')")
	public ResponseEntity<PermissionResponseDTO> findById(@PathVariable @ExistedId(Permission.class) Long id) {
		PermissionResponseDTO permissionDTO = permissionService.findById(id);
		return ResponseEntity.ok(permissionDTO);
	}

	/**
	 * Create a permission and returns {@code Created} status.
	 * 
	 * @param permissionDTO
	 */
	@PostMapping
	@PreAuthorize("hasRole('PERMISSION_CREATE')")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@Valid @RequestBody PermissionCreateRequestDTO permissionDTO) {
		permissionService.save(permissionDTO);
	}

	/**
	 * Update the existing permission
	 * 
	 * @param id
	 *            - Existing permission ID
	 * @param permissionDTO
	 * @return {@code OK} status after updation
	 */
	@PatchMapping("{id}")
	@PreAuthorize("hasRole('PERMISSION_UPDATE')")
	public ResponseEntity<Object> update(@PathVariable @ExistedId(Permission.class) Long id,
			@Valid @RequestBody PermissionUpdateRequestDTO permissionDTO) {
		permissionService.update(id, permissionDTO);
		return ResponseEntity.ok().build();
	}

	/**
	 * deletes permission and {@code OK} status
	 * 
	 * @param id-
	 *            Exisitng permission ID
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('PERMISSION_DELETE')")
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable @ExistedId(Permission.class) Long id) {
		permissionService.delete(id);
	}
}