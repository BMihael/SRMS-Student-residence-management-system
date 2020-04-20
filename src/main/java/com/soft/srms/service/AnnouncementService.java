package com.soft.srms.service;

import com.soft.srms.model.Announcement;
import com.soft.srms.model.User;

import java.util.List;

/**
 * Interface for storing and retrieving announcements from a database.
 */
public interface AnnouncementService {
    /**
     * Stores and returns the new announcement created by the user.
     *
     * @param user          author of the announcement
     * @param announcement  new announcement
     * @return              new announcement
     */
    Announcement createAnnouncement(User user, Announcement announcement);

    /**
     * Returns announcements which are not expired.
     *
     * @return announcements which are not expired
     */
    List<Announcement> getCurrentAnnouncements();
}
