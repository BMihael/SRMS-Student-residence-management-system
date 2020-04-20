package com.soft.srms.service;

import com.soft.srms.model.Room;

/**
 * Interface for retrieving rooms from a database.
 */
public interface RoomService {
    /**
     * Returns a spare room depending on the gender of the person needing it.
     *
     * @param gender        gender of the person needing a room
     * @return              room which can accommodate the person with given gender
     */
    Room findSpareRoom(String gender);

    /**
     * Returns number of empty rooms.
     *
     * @return number of emptry rooms
     */
    Long getEmptyRoomCount();

    /**
     * Returns number of rooms that are occupied but not at full capacity.
     *
     * @return number of rooms that are occupied but not at full capacity
     */
    Long getHalfEmptyRoomCount();

    /**
     * Returns number of full rooms.
     *
     * @return number of full rooms
     */
    Long getFullRoomCount();
}
