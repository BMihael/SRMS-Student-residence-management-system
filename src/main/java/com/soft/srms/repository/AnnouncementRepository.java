package com.soft.srms.repository;

import com.soft.srms.model.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for more specific CRUD operations on an announcement.
 */
@Transactional
public interface AnnouncementRepository extends CrudRepository<Announcement, Long> {
    /**
     * Returns announcements which are ongoing in the time period defined by the start date and end date.
     * The announcements are sorted descending by creation date.
     *
     * @param startDate     start date
     * @param endDate       end date
     * @return              announcements that are ongoing in the specified time period
     */
    List<Announcement> findByCreationDateBeforeAndExpirationDateAfterOrderByCreationDateDesc(LocalDateTime startDate, LocalDateTime endDate);
}
