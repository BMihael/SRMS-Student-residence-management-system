package com.soft.srms.service;

import com.soft.srms.model.*;
import com.soft.srms.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service that implements interface for storing and retrieving applications from a database.
 */
@Service
public class DefaultApplicationService implements ApplicationService {

    private RoomService roomService;
    private ApplicationRepository applicationRepository;
    private RoleService roleService;

    /**
     * Creates a new instance of the class DefaultApplicationService with the provided repositories.
     *
     * @param roomService               room repository
     * @param applicationRepository     application repository
     * @param roleService               role service
     */
    public DefaultApplicationService(RoomService roomService, ApplicationRepository applicationRepository, RoleService roleService) {
        this.roomService = roomService;
        this.applicationRepository = applicationRepository;
        this.roleService = roleService;
    }

    /**
     * Stores, processes and returns the new application created by the user.
     *
     * @param user          author of the application
     * @param application   new application
     * @return              new application
     */
    @Override
    public Application createApplication(User user, Application application) {
        application.setUser(user);
        application.setCreationDate(LocalDateTime.now());
        application.setApproved(false);

        moveStudent(application);

        return applicationRepository.save(application);
    }

    /**
     * Deletes the user's application and processes the waiting list.
     *
     * @param user user whose application will be deleted
     */
    @Override
    public void deleteApplication(User user) {
        roleService.updateUserRole(user, UserRole.ROLE_STUDENT);
        Application application = applicationRepository.findById_user(user.getId());
        applicationRepository.delete(application);

        moveStudentsInWaitingList();
    }

    private void moveStudentsInWaitingList() {
        Application nextApplication = firstWaitingApplication();
        if(nextApplication != null) {
            if(moveStudent(nextApplication)) moveStudentsInWaitingList();
        }
    }

    private boolean moveStudent(Application application) {
        Room spareRoom = roomService.findSpareRoom(application.getGender().name());
        if(spareRoom != null) {
            application.setApproved(true);
            application.setTenant(new Tenant(application, spareRoom));
            roleService.updateUserRole(application.getUser(), UserRole.ROLE_TENANT);
            return true;
        } else {
            return false;
        }
    }

    private Application firstWaitingApplication() {
        return applicationRepository.findFirstByApprovedFalseOrderByCreationDateAsc();
    }

    /**
     * Returns the admitted students' applications.
     *
     * @return admitted students' applications
     */
    @Override
    public List<Application> getTenantList(){
        return applicationRepository.getTenantList();
    }

    /**
     * Returns the applications of students on the waiting list.
     *
     * @return applications of students on the waiting list
     */
    @Override
    public List<Application> getWaitingList(){
        return applicationRepository.getWaitingList();
    }

    /**
     * Returns the application submitted by the author with provided primary key.
     *
     * @param id    primary key of the author
     * @return      application which was submitted by the author
     */
    @Override
    public Application findApplicationByUserId(Long id) {
        return applicationRepository.findById_user(id);
    }

    /**
     * Checks if the given JMBAG was already used in a submitted application.
     *
     * @param jmbag     JMBAG
     * @return          confirmation if the JMBAG was used in an application
     */
    @Override
    public boolean isJmbagTaken(String jmbag) {
        return applicationRepository.existsByJmbag(jmbag);
    }

    /**
     * Returns number of applications by gender and approval status.
     *
     * @param approval  approval status
     * @param gender    gender
     * @return          number of applications by gender and approval status
     */
    @Override
    public Long getApplicationCountByApprovalStatusAndGender(boolean approval, Gender gender) {
        return applicationRepository.countByApprovedEqualsAndGenderEquals(approval, gender);
    }
}
