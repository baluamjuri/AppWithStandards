package org.balu.learn.userservice.transformers;

import java.util.List;
import java.util.stream.Collectors;

import org.balu.learn.userservice.dto.UserResponseDTO;
import org.balu.learn.userservice.entity.User;
import org.balu.learn.userservice.entity.UserProfile;
import org.springframework.stereotype.Component;

/**
 * Converts the Entity to DTO and vice versa.
 * @author amjuribv
 *
 */

@Component
public class UserTransformer {
	/**
	 * Converts {@link User} to {@link UserResponseDTO}. If profile information is
	 * not existed then injects {@code null} to the profile related properties.
	 * 
	 * @param user
	 * @return {@link UserResponseDTO}
	 */
	public UserResponseDTO convert(User user) {
		UserProfile profile = user.getUserProfile();
		if(profile!=null)
			return convert(profile);
		else {
			return UserResponseDTO.builder()
					.id(user.getId())
					.displayName(user.getDisplayName()).build();
		}
	}
	
	/**
	 * Converts list of {@link User}s to list of {@link UserResponseDTO}s. If
	 * profile information is not existed then injects {@code null} to the profile
	 * related properties.
	 * 
	 * @param users
	 * @return List of {@link UserResponseDTO}s
	 */
	public List<UserResponseDTO> convert(List<User> users){
		return users.stream()
				.map(this::convert)
				.collect(Collectors.toList());
	}
	
	/**
	 * Converts {@link UserProfile} to {@link UserResponseDTO}.
	 * @param profile
	 * @return {@link UserResponseDTO}
	 */
	public UserResponseDTO convert(UserProfile profile) {
		User user = profile.getUser();
		return UserResponseDTO.builder()
				.id(user.getId())
				.displayName(user.getDisplayName())
				.email(profile.getEmail())
				.gender(profile.getGender())
				.mobile(profile.getMobile())
				.build();
	}
}

