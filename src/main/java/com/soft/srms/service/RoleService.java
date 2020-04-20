package com.soft.srms.service;

import com.soft.srms.model.Role;
import com.soft.srms.model.User;
import com.soft.srms.model.UserRole;

/**
 * Interface for updating user roles in a database.
 */
public interface RoleService {
    /**
     * Updates and returns the new role of the user.
     *
     * @param user          user whose role is updated
     * @param userRole      new user's role
     * @return              new user's role
     */
    Role updateUserRole(User user, UserRole userRole);
}
