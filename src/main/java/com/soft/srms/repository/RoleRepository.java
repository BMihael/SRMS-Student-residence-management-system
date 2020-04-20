package com.soft.srms.repository;

import com.soft.srms.model.Role;
import com.soft.srms.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface for more specific CRUD operations on a role.
 */
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {

    /**
     * Returns a role given to the user.
     *
     * @param user      user
     * @return          user's role
     */
    public Role findByUser(User user);
}
