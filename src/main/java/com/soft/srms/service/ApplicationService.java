package com.soft.srms.service;

import com.soft.srms.model.Application;
import com.soft.srms.model.Gender;
import com.soft.srms.model.User;

import java.util.List;

/**
 * Interface for storing and retrieving applications from a database.
 */
public interface ApplicationService {
    /**
     * Stores, processes and returns the new application created by the user.
     *
     * @param user          author of the application
     * @param application   new application
     * @return              new application
     */
    Application createApplication(User user, Application application);

    /**
     * Deletes the user's application and processes the waiting list.
     *
     * @param user user whose application will be deleted
     */
    void deleteApplication(User user);

    /**
     * Returns the admitted students' applications.
     *
     * @return admitted students' applications
     */
    List<Application> getTenantList();

    /**
     * Returns the applications of students on the waiting list.
     *
     * @return applications of students on the waiting list
     */
    List<Application> getWaitingList();

    /**
     * Returns the application submitted by the author with provided primary key.
     *
     * @param id    primary key of the author
     * @return      application which was submitted by the author
     */
    Application findApplicationByUserId(Long id);

    /**
     * Checks if the given JMBAG was already used in a submitted application.
     *
     * @param jmbag     JMBAG
     * @return          confirmation if the JMBAG was used in an application
     */
    boolean isJmbagTaken(String jmbag);

    /**
     * Returns number of applications by gender and approval status.
     *
     * @param approval  approval status
     * @param gender    gender
     * @return          number of applications by gender and approval status
     */
    Long getApplicationCountByApprovalStatusAndGender(boolean approval, Gender gender);
}
