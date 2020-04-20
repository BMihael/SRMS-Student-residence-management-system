package com.soft.srms.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * A room is a place provided by dormitory for housing.
 * It consists of a name and a maximum capacity. Once put in use, the room contains information about the
 * tenants.
 * <br>
 * This class represents the dormitory room data stored in a database.
 */
@Entity
@Table(name = "rooms")
public class Room implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "room", targetEntity = Tenant.class , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tenant> tenant;

    /**
     * Creates a new instance of the class Room.
     */
    public Room() {}

    /**
     * Returns the primary key of this Room.
     *
     * @return value of primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the primary key of this Room.
     *
     * @param id value of primary key
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of this Room.
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this Room.
     *
     * @param name value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the maximum capacity of this Room.
     *
     * @return value of  maximum capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets the maximum capacity of this Room.
     *
     * @param capacity value of maximum capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns the tenants of this Room.
     *
     * @return value of tenants
     */
    public List<Tenant> getTenant() {
        return tenant;
    }

    /**
     * Sets the tenants of this Room.
     *
     * @param tenant value of tenants
     */
    public void setTenant(List<Tenant> tenant) {
        this.tenant = tenant;
    }
}
