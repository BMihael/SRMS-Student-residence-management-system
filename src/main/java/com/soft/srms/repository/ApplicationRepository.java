package com.soft.srms.repository;

import com.soft.srms.model.Application;
import com.soft.srms.model.Gender;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for more specific CRUD operations on an application.
 */
@Transactional
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    /**
     * Returns admitted students' applications.
     *
     * @return admitted students' applications
     */
    @Query(value = "SELECT a.* " +
            "FROM users u " +
            "INNER JOIN applications a ON u.id = a.id_user " +
            "WHERE a.approved = 1 AND u.enabled = 1 " +
            "ORDER BY a.creation_date ASC", nativeQuery = true)
    List<Application> getTenantList();

    /**
     * Returns the applications of students on the waiting list.
     * @return applications of students on the waiting list
     */
    @Query(value = "SELECT a.* " +
            "FROM users u " +
            "INNER JOIN applications a ON u.id = a.id_user " +
            "WHERE a.approved = 0 AND u.enabled = 1 " +
            "ORDER BY a.creation_date ASC", nativeQuery = true)
    List<Application> getWaitingList();

    /**
     * Returns the application submitted by the author with provided primary key.
     *
     * @param id_user   primary key of the author
     * @return          application which was submitted by the author
     */
    @Query(value = "SELECT a.id, a.birth_date, a.gender, a.address, a.jmbag, a.university, a.creation_date, a.approved, a.id_user " +
            "FROM applications a " +
            "WHERE a.id_user = ?1", nativeQuery = true)
    Application findById_user(Long id_user);

    /**
     * Returns the first application on the waiting list.
     *
     * @return first application on the waiting list
     */
    Application findFirstByApprovedFalseOrderByCreationDateAsc();

    /**
     * Checks if the given JMBAG was used in an application.
     *
     * @param jmbag     JMBAG
     * @return          confirmation if the JMBAG was used in an application
     */
    boolean existsByJmbag(String jmbag);

    /**
     * Returns number of applications by gender and approval status.
     *
     * @param approved  approval status
     * @param gender    gender
     * @return          number of applications by gender and approval status
     */
    Long countByApprovedEqualsAndGenderEquals(boolean approved, Gender gender);
}
