package com.soft.srms.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A user is a registered person who has access to the application.
 * A user entry consists of the following personal information: first name, last name, email as username and password.
 * The password is stored encrypted.
 * <br>
 * This class represents the user data stored in a database.
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    @NotEmpty(message = "")
    @Size(min=3, max=20, message = "First name must contain between 3 and 20 characters.")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message = "")
    @Size(min=3, max=20, message = "Last name must contain between 3 and 20 characters.")
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name="username", unique = true)
    @NotEmpty(message = "")
    @Size(min=3, max=20, message = "Username must contain between 3 and 20 characters.")
    private String username;

    @Column(name="password")
    @NotEmpty(message = "")
    @Size(min=3, message = "Password must contain at least 3 characters.")
    private String password;

    @Column(name="enabled")
    private boolean enabled;

    @OneToOne(mappedBy="user",fetch=FetchType.LAZY)
    private Application application;

    @OneToMany(mappedBy = "user", targetEntity = Role.class , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany(mappedBy = "user", targetEntity = Announcement.class , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "user", targetEntity = Complaint.class , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Complaint> complaints;

    /**
     * Creates a new instance of the class User.
     */
    public User() {
        this.roles = new ArrayList<>();
        this.announcements = new ArrayList<>();
        this.complaints = new ArrayList<>();
    }

    /**
     * Returns the primary key of this User.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this User.
     *
     * @param id value of primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the first name of this User.
     *
     * @return value of first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this User.
     *
     * @param firstName value of first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of this User.
     *
     * @return value of last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of this User.
     *
     * @param lastName value of last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the username of this User.
     *
     * @return value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this User.
     *
     * @param username value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password of this User.
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of this User.
     *
     * @param password value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the account status of this User.
     *
     * @return value of account status
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the account status of this User.
     *
     * @param enabled value of account status
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Returns the application of this User.
     *
     * @return value of application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application of this User.
     *
     * @param application value of application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Returns the roles of this User.
     *
     * @return value of roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles of this User.
     *
     * @param roles value of roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Returns the announcements of this User.
     *
     * @return value of announcements
     */
    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    /**
     * Sets the announcements of this User.
     *
     * @param announcements value of announcements
     */
    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    /**
     * Returns the complaints of this User.
     *
     * @return value of complaints
     */
    public List<Complaint> getComplaints() {
        return complaints;
    }

    /**
     * Sets the complaints of this User.
     *
     * @param complaints value of complaints
     */
    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
}
