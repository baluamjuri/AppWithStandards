package org.balu.learn.userservice.repository;

import java.util.Optional;

import org.balu.learn.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	/**
	 * Retrieves the user by its username
	 * @param username
	 * @return the User with the given id or {@literal Optional#empty()} if none found
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Retrieves the user by its id
	 * @param id
	 * @return
	 */
	User getById(long id);
	
	/**
	 * Checks whether the role is mapped to the user or not.
	 * @param userId
	 * @param roleId
	 * @return {@code true} if mapping existed else {@code false}
	 */
	boolean existsByIdAndRoles_Id(Long userId, Long roleId);
}