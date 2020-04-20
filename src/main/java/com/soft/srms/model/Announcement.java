package com.soft.srms.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An announcement is a notification containing useful information relating to the dormitory.
 * It consists of a title, body containing the notification, creation date, expiration date and the user as
 * its author.
 * <br>
 * This class represents the dormitory announcement data stored in a database.
 */
@Entity
@Table(name="announcements")
public class Announcement implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    @NotEmpty(message = "")
    @Size(min=5, max=30, message = "Title must contain between 5 and 30 characters.")
    private String title;

    @Column(name="body")
    @NotEmpty(message = "Body must not be empty.")
    private String body;

    @Column(name="creation_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime creationDate;

    @Column(name="expiration_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    /**
     * Creates a new instance of the class Announcement.
     */
    public Announcement() {}

    /**
     * Returns the primary key of this Announcement.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this Announcement.
     *
     * @param id value of primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the title of this Announcement.
     *
     * @return value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this Announcement.
     *
     * @param title value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the body of this Announcement.
     *
     * @return value of body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of this Announcement.
     *
     * @param body value of body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the creation date of this Announcement.
     *
     * @return value of creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of this Announcement.
     *
     * @param creationDate value of creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the expiration date of this Announcement.
     *
     * @return value of expiration date
     */
    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of this Announcement.
     *
     * @param expirationDate value of expiration date
     */
    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Returns the author of this Announcement.
     *
     * @return value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the author of this Announcement.
     *
     * @param user value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns author and creation date of this Announcement.
     *
     * @return formatted String containing author's full name and creation date
     */
    public String getAuthorAndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy.' in 'H:mm");
        return this.user.getFirstName() + ' ' + user.getLastName() + ' ' + this.creationDate.format(formatter);
    }
}
