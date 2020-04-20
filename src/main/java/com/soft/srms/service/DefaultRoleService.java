package com.soft.srms.service;

import com.soft.srms.model.Role;
import com.soft.srms.model.User;
import com.soft.srms.model.UserPrincipal;
import com.soft.srms.model.UserRole;
import com.soft.srms.repository.RoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service that implements interface for updating user roles in a database.
 */
@Service
public class DefaultRoleService implements RoleService {

    private RoleRepository roleRepository;

    /**
     * Creates a new instance of the class DefaultRoleService with the provided role repository.
     *
     * @param roleRepository role repository
     */
    public DefaultRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Updates and returns the new role of the user.
     *
     * @param user          user whose role is updated
     * @param userRole      new user's role
     * @return              new user's role
     */
    @Override
    public Role updateUserRole(User user, UserRole userRole){
        Role role = roleRepository.findByUser(user);
        role.setRole(userRole);
        user.setRoles(Collections.singletonList(role));

        reloadAuthentication();

        return roleRepository.save(role);
    }

    private void reloadAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userPrincipal, auth.getCredentials(), userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
