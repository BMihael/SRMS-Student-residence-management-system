package com.soft.srms.service;

import com.soft.srms.model.Complaint;
import com.soft.srms.model.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for storing and retrieving complaints from a database.
 */
public interface ComplaintService {
    /**
     * Stores and returns the new complaint created by the user.
     *
     * @param user          author of the complaint
     * @param complaint     new complaint
     * @return              new complaint
     */
    Complaint createComplaint(User user, Complaint complaint);

    /**
     * Returns unsolved complaints.
     *
     * @return unsolved complaints
     */
    List<Complaint> getUnsolvedComplaints();

    /**
     * Sets the status of the complaint to solved if it exists.
     *
     * @param id    primary key of the complaint
     * @return      confirmation of successfully marking the complaint as solved
     */
    boolean solveComplaint(Long id);

    /**
     * Returns number of complaints created at given date.
     *
     * @param date  date
     * @return      number of complaints created at given date
     */
    Long getComplaintCountByCreationDate(LocalDate date);

    /**
     * Returns number of complaints created between start date and end date.
     *
     * @param startDate     start date
     * @param endDate       end date
     * @return              number of complaints created between start date and end date
     */
    Long getComplaintCountByCreationDateBetween(LocalDate startDate, LocalDate endDate);
}
