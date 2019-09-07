package org.balu.learn.userservice.repository;

import org.balu.learn.userservice.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	Permission getById(Long id);

	/**
	 * Checks whether the given permission name existed or not
	 * 
	 * @param name
	 *            of the permission
	 * @return {@code true} if permission name existed else {@code false}
	 */
	boolean existsByName(String name);
}
