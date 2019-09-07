package org.balu.learn.userservice.repository;

import org.balu.learn.userservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role getById(long id);

	/**
	 * Checks whether the given role and permission are mapped or not
	 * 
	 * @param roleId
	 *            - ID of the role
	 * @param permissionId
	 *            - ID of the permission
	 * @return {@code true} if role and permission are mapped else {@code false}
	 */
	boolean existsByIdAndPermissions_Id(Long roleId, Long permissionId);
}
