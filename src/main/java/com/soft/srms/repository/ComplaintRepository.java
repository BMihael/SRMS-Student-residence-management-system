package com.soft.srms.repository;

import com.soft.srms.model.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for more specific CRUD operations on a complaint.
 */
@Transactional
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    /**
     * Returns complaints marked as unsolved.
     * The complaints are sorted descending by creation date.
     *
     * @return complaints marked as unsolved
     */
    List<Complaint> findAllBySolvedFalseOrderByCreationDateDesc();

    /**
     * Returns complaints created between start date and end date.
     *
     * @param start     start date
     * @param end       end date
     * @return          complaints created between start date and end date
     */
    Long countByCreationDateBetween(LocalDateTime start, LocalDateTime end);
}
