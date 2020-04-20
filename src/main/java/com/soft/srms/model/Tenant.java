package com.soft.srms.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A tenant is a entry that links a user's application with a room.
 * <br>
 * This class represents the tenantry data stored in a database.
 */
@Entity
@Table(name = "tenants")
public class Tenant implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="id_application")
    private Application application;

    @OneToOne
    @JoinColumn(name="id_room")
    private Room room;

    /**
     * Creates a new instance of the class Tenant.
     */
    public Tenant() {}

    /**
     * Creates a new instance of the class Tenant with the provided application and room.
     *
     * @param application approved application for a room
     * @param room provided room
     */
    public Tenant(Application application, Room room) {
        this.application = application;
        this.room = room;
    }

    /**
     * Returns the primary key of this Tenant.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary of this Tenant.
     *
     * @param id value of primary
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the application of this Tenant.
     *
     * @return value of application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * Sets the application of this Tenant.
     *
     * @param application value of application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     * Returns the room of this Tenant.
     *
     * @return value of room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room of this Tenant.
     *
     * @param room value of room
     */
    public void setRoom(Room room) {
        this.room = room;
    }
}
