package org.balu.learn.userservice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.balu.learn.userservice.dto.ChangePasswordRequestDTO;
import org.balu.learn.userservice.dto.ProfileCreateRequestDTO;
import org.balu.learn.userservice.dto.ProfileUpdateRequestDTO;
import org.balu.learn.userservice.dto.RoleResponseDTO;
import org.balu.learn.userservice.dto.SignUpForm;
import org.balu.learn.userservice.dto.UserResponseDTO;
import org.balu.learn.userservice.entity.Role;
import org.balu.learn.userservice.entity.User;
import org.balu.learn.userservice.entity.UserProfile;
import org.balu.learn.userservice.repository.RoleRepository;
import org.balu.learn.userservice.repository.UserProfileRepository;
import org.balu.learn.userservice.repository.UserRepository;
import org.balu.learn.userservice.service.UserService;
import org.balu.learn.userservice.transformers.UserTransformer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserProfileRepository profileRepository;
	
	@Autowired
	private UserTransformer userTransformer;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserResponseDTO findUserDetails(long id) {
		User user = userRepository.getById(id);
		return userTransformer.convert(user);
	}
	
	@Override
	public UserResponseDTO findUserDetails(String username) {
		Optional<User> proxy = userRepository.findByUsername(username);
		User user = proxy.get();
		return userTransformer.convert(user);
	}

	@Override
	public Long save(SignUpForm signUpDTO) {
		User userEntity = mapper.map(signUpDTO, User.class);
		userEntity.setPassword(
				passwordEncoder.encode(signUpDTO.getPassword()));
		userEntity.setActive(true);
		User createdUser = userRepository.save(userEntity);
		return createdUser.getId();
	}

	@Override
	public void update(long id, ProfileUpdateRequestDTO profileDTO) {
		Optional<UserProfile> proxy = profileRepository.findByUser_id(id);
		UserProfile profile = proxy.get();		

		if(!StringUtils.isEmpty(profileDTO.getDisplayName())){
			User user = profile.getUser();
			user.setDisplayName(profileDTO.getDisplayName());
			userRepository.save(user);
		}
		
		if(!StringUtils.isEmpty(profileDTO.getEmail()))
			profile.setEmail(profileDTO.getEmail());
		
		if(!StringUtils.isEmpty(profileDTO.getMobile()))
			profile.setMobile(profileDTO.getMobile());
		
		profile =  profileRepository.save(profile);
	}

	@Override
	public boolean delete(long id) {		
		Optional<User> proxy = userRepository.findById(id);
		User persistentUser = proxy.get();
		persistentUser.setActive(false);
		userRepository.save(persistentUser);
		return true;
	}

	@Override
	public List<UserResponseDTO> findAll() {
		List<User> users = userRepository.findAll();
		return userTransformer.convert(users);
	}

	@Override
	public void changePassword(String username, ChangePasswordRequestDTO changePasswordDTO) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		User user = userRepository.findByUsername(username).get();
		user.setPassword(encoder.encode(changePasswordDTO.getNewPassword()));
		userRepository.save(user);
	}

	@Override
	public List<RoleResponseDTO> findRolesByUserId(long id) {
		Optional<User> proxy = userRepository.findById(id);
		User user = proxy.get();
		List<RoleResponseDTO> roleDTOs = user.getRoles()
								.stream()
								.map(role -> mapper.map(role, RoleResponseDTO.class))
								.collect(Collectors.toList());
		return roleDTOs;
	}

	@Override
	public boolean addRoleToUser(long userId, long roleId) {
		Role role = roleRepository.getById(roleId);
		User user = userRepository.findById(userId).get();
		
		user.getRoles().add(role);
		
		userRepository.save(user);
		
		return true;
	}

	@Override
	public boolean deleteRoleFromUser(long userId, long roleId) {
		Role role = roleRepository.getById(roleId);
		User user = userRepository.findById(userId).get();
		
		user.getRoles().remove(role);
		
		userRepository.save(user);
		return true;
	}
	
	@Override
	public Long saveProfile(Long userId, ProfileCreateRequestDTO profileDTO) {
		UserProfile profile = mapper.map(profileDTO, UserProfile.class);
		User user = new User();
		user.setId(userId);
		profile.setUser(user);
		profile = profileRepository.save(profile);
		return profile.getId();
	}
}