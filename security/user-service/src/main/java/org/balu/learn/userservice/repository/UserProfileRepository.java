package org.balu.learn.userservice.repository;

import java.util.Optional;

import org.balu.learn.userservice.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	/**
	 * Fetches the user-profile based on the user ID
	 * 
	 * @param id
	 *            - ID of the user
	 * @return - the user-profile with the given ID or {@literal Optional#empty()}
	 *         if none found
	 */
	Optional<UserProfile> findByUser_id(long id);
}
