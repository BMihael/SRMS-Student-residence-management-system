package com.soft.srms.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A complaint is a form submitted by tenants to notify dormitory supervisors about a problem.
 * It consists of a body containing the complaint, creation date, current status and the user as its author.
 * <br>
 * This class represents the tenants' complaint data stored in a database.
 */
@Entity
@Table(name="complaints")
public class Complaint implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="body")
    @NotEmpty(message = "Body must not be empty.")
    private String body;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @Column(name="solved")
    private boolean solved;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    /**
     * Creates a new instance of the class Complaint.
     */
    public Complaint() {}

    /**
     * Returns the primary key of this Complaint.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this Complaint.
     *
     * @param id value of primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the body of this Complaint.
     *
     * @return value of body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the body of this Complaint.
     *
     * @param body value of body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the creation date of this Complaint.
     *
     * @return value of creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of this Complaint.
     *
     * @param creationDate value of creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the status of this Complaint.
     *
     * @return value of status
     */
    public boolean isSolved() {
        return solved;
    }

    /**
     * Sets the status of this Complaint.
     *
     * @param solved value of status
     */
    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    /**
     * Returns the author of this Complaint.
     *
     * @return value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the author of this Complaint.
     *
     * @param user value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
