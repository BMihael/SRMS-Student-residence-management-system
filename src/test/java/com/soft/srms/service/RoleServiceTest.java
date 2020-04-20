package com.soft.srms.service;

import com.soft.srms.model.Role;
import com.soft.srms.model.User;
import com.soft.srms.model.UserPrincipal;
import com.soft.srms.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    User user;

    @BeforeEach
    void initUser(){
        user = new User();
        user.setId(2L);
        user.setFirstName("Allan");
        user.setLastName("Graves");
        user.setUsername("student@faks.hr");
        user.setPassword("$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC");
        user.setEnabled(true);
        user.getRoles().add(new Role(user, UserRole.ROLE_STUDENT));

        UserPrincipal userPrincipal = new UserPrincipal(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword(), userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

    }
    @Test
    void updateUserRole() {
        Role role = roleService.updateUserRole(user, UserRole.ROLE_TENANT);
        assertEquals(role.getRole(), UserRole.ROLE_TENANT, () -> "updateUserRole method should update user role");
    }
}