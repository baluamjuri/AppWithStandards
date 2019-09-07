package org.balu.learn.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.balu.learn.userservice.dto.PermissionResponseDTO;
import org.balu.learn.userservice.dto.RoleCreateRequestDTO;
import org.balu.learn.userservice.dto.RoleResponseDTO;
import org.balu.learn.userservice.dto.RoleUpdateRequestDTO;
import org.balu.learn.userservice.entity.Permission;
import org.balu.learn.userservice.entity.Role;
import org.balu.learn.userservice.repository.PermissionRepository;
import org.balu.learn.userservice.repository.RoleRepository;
import org.balu.learn.userservice.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.util.StringUtils;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public RoleResponseDTO findById(long id) {
		Role role = roleRepository.getById(id);
		return mapper.map(role, RoleResponseDTO.class);
	}

	@Override
	public Long save(RoleCreateRequestDTO roleDTO) {
		Role roleEntity = mapper.map(roleDTO, Role.class);
		roleEntity.setId(null);
		roleEntity.setActive(true);
		Role createdRole = roleRepository.save(roleEntity);
		return createdRole.getId();
	}

	@Override
	public RoleResponseDTO update(long id, RoleUpdateRequestDTO roleDTO) {

		Role persistentRole = roleRepository.getById(id);
		if (StringUtils.isNotBlank(roleDTO.getName()))
			persistentRole.setName(roleDTO.getName());

		if (StringUtils.isNotBlank(roleDTO.getDescription()))
			persistentRole.setDescription(roleDTO.getDescription());

		Role updatedRole = roleRepository.save(persistentRole);
		return mapper.map(updatedRole, RoleResponseDTO.class);
	}

	@Override
	public boolean delete(long id) {
		Role roleToBeUpdate = roleRepository.getById(id);
		;
		roleToBeUpdate.setActive(false);
		roleRepository.save(roleToBeUpdate);
		return true;
	}

	@Override
	public List<RoleResponseDTO> findAll() {
		List<Role> roles = roleRepository.findAll();
		List<RoleResponseDTO> roleDTOs = roles.stream().map(role -> mapper.map(role, RoleResponseDTO.class))
				.collect(Collectors.toList());
		return roleDTOs;
	}

	@Override
	public List<PermissionResponseDTO> findPermissionsByRoleId(long id) {

		Role role = roleRepository.getById(id);
		List<PermissionResponseDTO> permissionDTOs = role.getPermissions().stream()
				.map(permission -> mapper.map(permission, PermissionResponseDTO.class))
				.collect(Collectors.toList());
		return permissionDTOs;
	}

	@Override
	public boolean addPermissionToRole(long roleId, long permissionId) {
		Permission permissionEntity = permissionRepository.getOne(permissionId);

		Role roleEntity = roleRepository.getById(roleId);

		roleEntity.getPermissions().add(permissionEntity);

		roleRepository.save(roleEntity);
		return true;
	}

	@Override
	public boolean deletePermissionFromRole(long roleId, long permissionId) {
		Permission permissionEntity = permissionRepository.getOne(permissionId);

		Role roleEntity = roleRepository.getById(roleId);

		roleEntity.getPermissions().remove(permissionEntity);

		roleRepository.save(roleEntity);
		return true;
	}
}