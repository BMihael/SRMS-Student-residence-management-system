package com.soft.srms.service;

import com.soft.srms.model.Announcement;
import com.soft.srms.model.User;
import com.soft.srms.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service that implements interface for storing and retrieving announcements from a database.
 */
@Service
public class DefaultAnnouncementService implements AnnouncementService {

    private AnnouncementRepository announcementRepository;

    /**
     * Creates a new instance of the class DefaultAnnouncementService with the provided announcement repository.
     *
     * @param announcementRepository announcement repository
     */
    public DefaultAnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    /**
     * Stores and returns the new announcement created by the user.
     *
     * @param user          author of the announcement
     * @param announcement  new announcement
     * @return              new announcement
     */
    @Override
    public Announcement createAnnouncement(User user, Announcement announcement) {
        announcement.setCreationDate(LocalDateTime.now());
        announcement.setUser(user);
        return announcementRepository.save(announcement);
    }

    /**
     * Returns announcements which are not expired.
     *
     * @return announcements which are not expired
     */
    @Override
    public List<Announcement> getCurrentAnnouncements() {
        return announcementRepository.findByCreationDateBeforeAndExpirationDateAfterOrderByCreationDateDesc(LocalDateTime.now(),LocalDateTime.now());
    }
}
