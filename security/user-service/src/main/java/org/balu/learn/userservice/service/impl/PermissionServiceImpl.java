package org.balu.learn.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.balu.learn.userservice.dto.PermissionCreateRequestDTO;
import org.balu.learn.userservice.dto.PermissionResponseDTO;
import org.balu.learn.userservice.dto.PermissionUpdateRequestDTO;
import org.balu.learn.userservice.entity.Permission;
import org.balu.learn.userservice.repository.PermissionRepository;
import org.balu.learn.userservice.service.PermissionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<PermissionResponseDTO> findAll() {
		List<Permission> permissions = permissionRepository.findAll();
		List<PermissionResponseDTO> permissionDTOs = permissions.stream()
									.map(permission -> mapper.map(permission, PermissionResponseDTO.class))
									.collect(Collectors.toList());
		return permissionDTOs;
	}

	@Override
	public PermissionResponseDTO findById(long id) {
		Permission permission = permissionRepository.getById(id);
		return mapper.map(permission, PermissionResponseDTO.class);
	}

	@Override
	public Long save(PermissionCreateRequestDTO permissionDTO) {
		Permission permissionEntity = mapper.map(permissionDTO, Permission.class); 
		permissionEntity.setId(null);
		permissionEntity.setActive(true);
		Permission createdPermission = permissionRepository.save(permissionEntity);
		return createdPermission.getId();
	}

	@Override
	public void update(long id, PermissionUpdateRequestDTO permissionDTO) {
		
		Permission persistentPermission = permissionRepository.getById(id);
		
		if(! StringUtils.isEmpty(permissionDTO.getName()))
			persistentPermission.setName(permissionDTO.getName());
		
		if(! StringUtils.isEmpty(permissionDTO.getDescription()))
			persistentPermission.setDescription(permissionDTO.getDescription());
			
		permissionRepository.save(persistentPermission);		
	}
	
	@Override
	public void delete(long id) {
		Permission permissionToBeUpdate = permissionRepository.getById(id);
		permissionToBeUpdate.setActive(false);
		permissionRepository.save(permissionToBeUpdate);
	}

}
