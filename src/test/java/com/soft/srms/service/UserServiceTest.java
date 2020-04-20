package com.soft.srms.service;

import com.soft.srms.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void initUser(){
        user = new User();
        user.setFirstName("Allan");
        user.setLastName("Graves");
        user.setUsername("student6@faks.hr");
        user.setPassword("student");
    }

    @Test
    @Order(2)
    void registerNewUserAccount(){
        User newlyCreatedUser = userService.createUserAccount(user);
        assertTrue(() -> {
            return user.getId().equals(newlyCreatedUser.getId()) &&
                    user.getFirstName().equals(newlyCreatedUser.getFirstName()) &&
                    user.getLastName().equals(newlyCreatedUser.getLastName()) &&
                    user.getUsername().equals(newlyCreatedUser.getUsername());
        }, () -> "registerNewUserAccount method should return newly created user");
    }

    @Test
    @Order(1)
    void isUsernameTaken(){
        assertFalse(userService.isUsernameTaken(user.getUsername()), () -> user.getUsername() + "should not be taken");
    }
}
