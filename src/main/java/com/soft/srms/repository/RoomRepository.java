package com.soft.srms.repository;

import com.soft.srms.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface for more specific CRUD operations on a room.
 */
@Transactional
public interface RoomRepository extends CrudRepository<Room, Long> {

    /**
     * Returns a spare room depending on the provided gender.
     *
     * @param gender        person's gender
     * @return              room which can accommodate the person with given gender
     */
    @Query(value = "SELECT r.id, r.name, r.capacity " +
            "FROM rooms r " +
            "LEFT OUTER JOIN tenants t ON r.id = t.id_room " +
            "LEFT OUTER JOIN applications a ON a.id = t.id_application " +
            "WHERE a.approved = 1 OR a.approved IS NULL " +
            "GROUP BY r.id, r.name, r.capacity " +
            "HAVING " +
            "(COUNT(t.id) < r.capacity) " +
            "AND a.gender = ?1 or a.gender is NULL " +
            "ORDER BY r.id ASC " +
            "LIMIT 1"
            , nativeQuery = true
    )
    Room findSpareRoom(String gender);

    /**
     * Counts rooms without tenants.
     *
     * @return number of rooms without tenants
     */
    @Query(value = "SELECT COUNT(r.id) " +
            "FROM rooms r " +
            "LEFT OUTER JOIN tenants t ON r.id = t.id_room " +
            "WHERE t.id_room IS NULL"
            , nativeQuery = true
    )
    Long countEmptyRooms();

    /**
     * Counts rooms that are occupied without maximum capacity.
     *
     * @return number of rooms that are occupied without maximum capacity
     */
    @Query(value = "SELECT COUNT(id) " +
            "FROM rooms " +
            "WHERE id IN " +
            "(SELECT r.id FROM rooms r " +
            "LEFT OUTER JOIN tenants t " +
            "ON t.id_room=r.id " +
            "GROUP BY r.id " +
            "HAVING (COUNT(t.id)<r.capacity AND COUNT(t.id)>0))"
            , nativeQuery = true
    )
    Long countHalfEmptyRooms();

    /**
     * Counts rooms that are occupied with maximum capacity.
     *
     * @return number of rooms that are occupied with maximum capacity
     */
    @Query(value = "SELECT COUNT(id) " +
            "FROM rooms " +
            "WHERE id IN " +
            "(SELECT r.id FROM rooms r " +
            "LEFT OUTER JOIN tenants t " +
            "ON t.id_room=r.id " +
            "GROUP BY r.id " +
            "HAVING (COUNT(t.id)=r.capacity))"
            , nativeQuery = true
    )
    Long countFullRooms();
}
