package com.soft.srms.service;

import com.soft.srms.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationServiceTest {

    @Autowired
    private ApplicationService applicationService;

    private User user;
    private Application application;

    @BeforeEach
    void initUser() {
        user = new User();
        user.setId(2L);
        user.setFirstName("Allan");
        user.setLastName("Graves");
        user.setUsername("student@faks.hr");
        user.setPassword("$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC");
        user.setEnabled(true);
        user.getRoles().add(new Role(user, UserRole.ROLE_STUDENT));
        initApplication();

        UserPrincipal userPrincipal = new UserPrincipal(user);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(userPrincipal, userPrincipal.getPassword(), userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    private void initApplication() {
        application = new Application();
        application.setId(1L);
        application.setAddress("Zagreb");
        application.setUser(user);
        application.setBirthDate(LocalDate.now());
        application.setGender(Gender.MALE);
        application.setUniversity("TVZ");
        application.setJmbag("1234567899");
    }

    @Test
    @Order(1)
    void isJmbagTaken() {
        boolean jmbagTaken = applicationService.isJmbagTaken(application.getJmbag());
        assertFalse(jmbagTaken);
    }

    @Test
    @Order(2)
    void createApplication() {
        Application newlyApplication = applicationService.createApplication(user, application);
        assertTrue(() -> {
            return application.getAddress().equals(newlyApplication.getAddress()) &&
                    application.getGender().equals(newlyApplication.getGender()) &&
                    application.getBirthDate().equals(newlyApplication.getBirthDate()) &&
                    application.getJmbag().equals(newlyApplication.getJmbag()) &&
                    application.getId().equals(newlyApplication.getId());
        }, () -> "createApplication method should have create new application");
    }

    @Test
    @Order(3)
    void findApplicationByUserId() {
        Application applicationByUserId = applicationService.findApplicationByUserId(user.getId());
        assertTrue(() -> {
            return application.getAddress().equals(applicationByUserId.getAddress()) &&
                    application.getGender().equals(applicationByUserId.getGender()) &&
                    application.getBirthDate().equals(applicationByUserId.getBirthDate()) &&
                    application.getJmbag().equals(applicationByUserId.getJmbag()) &&
                    application.getId().equals(applicationByUserId.getId());
        }, () -> "findApplicationByUserId method should return user application");
    }

    @Test
    @Order(4)
    void getTenantList() {
        List<Application> tenantList = applicationService.getTenantList();
        assertEquals(1, tenantList.size());
    }

    @Test
    @Order(5)
    void getWaitingList() {
        List<Application> waitingList = applicationService.getWaitingList();
        assertEquals(0, waitingList.size());
    }

    @Test
    @Order(6)
    void deleteApplication() {
        applicationService.deleteApplication(application.getUser());
        assertNull(applicationService.findApplicationByUserId(application.getUser().getId()));
    }
}