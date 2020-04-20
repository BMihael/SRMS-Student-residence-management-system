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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class AnnouncementServiceTest {

    @Autowired
    private AnnouncementService announcementService;

    private User user;
    private Announcement announcement;

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
        initAnnouncement();

        UserPrincipal userPrincipal = new UserPrincipal(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword(), userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);

    }

    private void initAnnouncement(){
        announcement = new Announcement();
        announcement.setId(1L);
        announcement.setBody("body");
        announcement.setTitle("title");
        announcement.setExpirationDate(LocalDateTime.of(2021, Month.JULY, 29, 19, 30, 40));
    }
    @Test
    void addNewAnnouncementToAccount() {
        Announcement newlyCreatedAnnouncement = announcementService.createAnnouncement(user, announcement);
        assertTrue(() -> {
            return announcement.getBody().equals(newlyCreatedAnnouncement.getBody())
                    && announcement.getTitle().equals(newlyCreatedAnnouncement.getTitle())
                    && announcement.getId().equals(newlyCreatedAnnouncement.getId());
        }, () -> "addNewAnnouncementToAccount method should return newly crated announcement");
    }

    @Test
    void getCurrentAnnouncements() {
        List<Announcement> currentAnnouncementList = announcementService.getCurrentAnnouncements();
        assertEquals(1, currentAnnouncementList.size(), () -> "getCurrentAnnouncements method should return all currents announcements");
    }
}