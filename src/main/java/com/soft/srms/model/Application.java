package com.soft.srms.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * An application is a form submitted by students to acquire dormitory housing.
 * It consists of the following student's information: gender, birth date, address, JMBAG and attending university.
 * Also contains a creation date and approval status. Once approved, the application contains information about the
 * tenantry.
 * <br>
 * This class represents the students' application data stored in a database.
 */
@Entity
@Table(name="applications")
public class Application implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Column(name="address")
    @NotEmpty(message = "")
    @Size(min=3, max=255, message = "Address must contain between 3 and 255 characters.")
    private String address;

    @Column(name="jmbag")
    @NotEmpty(message = "")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid JMBAG. Must contain 10 digits.")
    private String jmbag;

    @Column(name="university")
    @NotEmpty(message = "")
    @Size(min=3, max=50, message = "University must contain between 3 and 50 characters.")
    private String university;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @Column(name="approved")
    private boolean approved;

    @OneToOne
    @JoinColumn(name="id_user")
    private User user;

    @OneToOne(mappedBy = "application", targetEntity = Tenant.class , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tenant tenant;

    /**
     * Creates a new instance of the class Application.
     */
    public Application() {}

    /**
     * Returns the primary key of this Application.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this Application.
     *
     * @param id value of primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the applicant's gender of this Application.
     *
     * @return value of gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the applicant's gender of this Application.
     *
     * @param gender value of gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Returns the applicant's birth date of this Application.
     *
     * @return value of birth date
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the applicant's birth date of this Application.
     *
     * @param birthDate value of birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Returns the applicant's address of this Application.
     *
     * @return value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the applicant's address of this Application.
     *
     * @param address value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the applicant's JMBAG of this Application.
     *
     * @return value of JMBAG
     */
    public String getJmbag() {
        return jmbag;
    }

    /**
     * Sets the applicant's JMBAG of this Application.
     *
     * @param jmbag value of JMBAG
     */
    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    /**
     * Returns the applicant's university of this Application.
     *
     * @return value of university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * Sets the applicant's university of this Application.
     *
     * @param university value of university
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     * Returns the creation date of this Application.
     *
     * @return value of creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of this Application.
     *
     * @param creationDate value of creation date
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the approval status of this Application.
     *
     * @return value of approval status
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets the approval status of this Application.
     *
     * @param approved value of approval status
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * Returns the author of this Application.
     *
     * @return value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the author of this Application.
     *
     * @param user value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the tenantry information relating to this Application.
     *
     * @return value of tenant
     */
    public Tenant getTenant() {
        return tenant;
    }

    /**
     * Sets the tenantry information relating to this Application.
     *
     * @param tenant value of tenant
     */
    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
