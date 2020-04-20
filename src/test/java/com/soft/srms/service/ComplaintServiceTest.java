package com.soft.srms.service;

import com.soft.srms.model.Complaint;
import com.soft.srms.model.Role;
import com.soft.srms.model.User;
import com.soft.srms.model.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ComplaintServiceTest {

    @Autowired
    private ComplaintService complaintService;

    private User user;
    private Complaint complaint;

    @BeforeEach
    void initUser(){
        user = new User();
        user.setId(2L);
        user.setFirstName("Allan");
        user.setLastName("Graves");
        user.setUsername("student@faks.hr");
        user.setPassword("$2y$12$fcb0ckD33FH550uFWcxvp.Z56JgFCK0AEw1/113RQUYGD0JlP.NjC");
        user.setEnabled(true);
        user.getRoles().add(new Role(user, UserRole.ROLE_TENANT));
        initComplaint();

    }

    private void initComplaint(){
        complaint = new Complaint();
        complaint.setBody("body");
    }

    @Test
    @Order(1)
    void addNewComplaintToAccount() {
        Complaint newlyCreatedComplaint = complaintService.createComplaint(user, complaint);
        assertTrue(() -> {
            return complaint.getId().equals(newlyCreatedComplaint.getId()) &&
                    complaint.getBody().equals(newlyCreatedComplaint.getBody()) &&
                    complaint.getCreationDate().equals(newlyCreatedComplaint.getCreationDate()) &&
                    complaint.getUser().equals(newlyCreatedComplaint.getUser());
        });
    }

    @Test
    @Order(2)
    void getUnsolvedComplaints() {
        List<Complaint> unsolvedComplaints = complaintService.getUnsolvedComplaints();
        assertEquals(1, unsolvedComplaints.size());
    }

    @Test
    @Order(3)
    void solveComplaint() {
        boolean isComplaintSolved = complaintService.solveComplaint(1l);
        assertTrue(isComplaintSolved);
    }
}