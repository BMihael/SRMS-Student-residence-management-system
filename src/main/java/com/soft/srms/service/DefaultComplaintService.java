package com.soft.srms.service;

import com.soft.srms.model.Complaint;
import com.soft.srms.model.User;
import com.soft.srms.repository.ComplaintRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service that implements interface for storing and retrieving complaints from a database.
 */
@Service
public class DefaultComplaintService implements ComplaintService {

    private ComplaintRepository complaintRepository;

    /**
     * Creates a new instance of the class DefaultComplaintService with the provided complaint repository.
     *
     * @param complaintRepository complaint repository
     */
    public DefaultComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    /**
     * Stores and returns the new complaint created by the user.
     *
     * @param user          author of the complaint
     * @param complaint     new complaint
     * @return              new complaint
     */
    @Override
    public Complaint createComplaint(User user, Complaint complaint) {
        complaint.setCreationDate(LocalDateTime.now());
        complaint.setUser(user);
        complaint.setSolved(false);
        return complaintRepository.save(complaint);
    }

    /**
     * Returns unsolved complaints.
     *
     * @return unsolved complaints
     */
    @Override
    public List<Complaint> getUnsolvedComplaints() {
        return complaintRepository.findAllBySolvedFalseOrderByCreationDateDesc();
    }

    /**
     * Sets the status of the complaint to solved if it exists.
     *
     * @param id    primary key of the complaint
     * @return      confirmation of successfully marking the complaint as solved
     */
    @Override
    public boolean solveComplaint(Long id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if(complaint.isPresent()) {
            complaint.get().setSolved(true);
            complaintRepository.save(complaint.get());
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns number of complaints created at given date.
     *
     * @param date  date
     * @return      number of complaints created at given date
     */
    @Override
    public Long getComplaintCountByCreationDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atStartOfDay().plusDays(1).minusNanos(1);
        return complaintRepository.countByCreationDateBetween(start, end);
    }

    /**
     * Returns number of complaints created between start date and end date.
     *
     * @param startDate     start date
     * @param endDate       end date
     * @return              number of complaints created between start date and end date
     */
    @Override
    public Long getComplaintCountByCreationDateBetween(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay().plusDays(1).minusNanos(1);
        return complaintRepository.countByCreationDateBetween(start, end);
    }
}
