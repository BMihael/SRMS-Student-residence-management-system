package com.soft.srms.service;

import com.soft.srms.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    private User user;

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
    }

    @Test
    void findSpareRoom() {
        Room spareRoom = roomService.findSpareRoom("MALE");
        assertEquals(1, spareRoom.getId(), () -> "findSpareRoom method should find spare male room");
    }
}