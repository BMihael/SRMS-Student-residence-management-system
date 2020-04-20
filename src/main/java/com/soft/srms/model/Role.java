package com.soft.srms.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A role is a entry that links a user with a type of role.
 * <br>
 * This class represents the users' role data stored in a database.
 */
@Entity
@Table(name="roles")
public class Role implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;

    /**
     * Creates a new instance of the class Role.
     */
    public Role() {}

    /**
     * Creates a new instance of the class Role with the provided user and type of role.
     *
     * @param user user the role applies to
     * @param role type of role
     */
    public Role(User user, UserRole role) {
        this.user = user;
        this.role = role;
    }

    /**
     * Returns the primary key of this Role.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this Role.
     *
     * @param id value of primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the type of role of this Role.
     *
     * @return value of role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets the type of role of this Role.
     *
     * @param role value of role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Returns the user of this Role.
     *
     * @return value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user of this Role.
     *
     * @param user value of user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
