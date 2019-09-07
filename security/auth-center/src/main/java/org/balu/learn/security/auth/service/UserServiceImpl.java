package org.balu.learn.security.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.balu.learn.security.auth.entity.Permission;
import org.balu.learn.security.auth.entity.Role;
import org.balu.learn.security.auth.entity.User;
import org.balu.learn.security.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		List<SimpleGrantedAuthority> authorities;
		
		try {
			//Converting Permission objects to spring authorites
			authorities = user.getRoles().stream().map(Role::getPermisssions).flatMap(List::stream).distinct()
					.map(this::toGrantedAuthority)
					.collect(Collectors.toList());
		} catch (NullPointerException npe) {
			throw new UsernameNotFoundException(npe.getMessage());
		}
		logger.info("Spring User get loading with the User details Username, password and authorities");
		
		//Converting user to spring user 
		return new org.springframework.security.core.userdetails.User(
														user.getUsername(), 
														user.getPassword(), 
														authorities);
		
	}

	/**
	 * converts {@link Permission} to {@link SimpleGrantedAuthority}
	 * @param permissionEntity
	 * @return {@link SimpleGrantedAuthority}
	 */
	private SimpleGrantedAuthority toGrantedAuthority(Permission permissionEntity) {
		return new SimpleGrantedAuthority(SPRING_ROLE_PREFIX+permissionEntity.getName());
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
