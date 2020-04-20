package com.soft.srms.service;

import com.soft.srms.model.Room;
import com.soft.srms.repository.RoomRepository;
import org.springframework.stereotype.Service;

/**
 * Service that implements interface for retrieving rooms from a database.
 */
@Service
public class DefaultRoomService implements RoomService {

    private RoomRepository roomRepository;

    /**
     * Creates a new instance of the class RoomService with the provided room repository.
     *
     * @param roomRepository room repository
     */
    public DefaultRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Returns a spare room depending on the gender of the person needing it.
     *
     * @param gender        gender of the person needing a room
     * @return              room which can accommodate the person with given gender
     */
    @Override
    public Room findSpareRoom(String gender) {
        return roomRepository.findSpareRoom(gender);
    }

    /**
     * Returns number of empty rooms.
     *
     * @return number of emptry rooms
     */
    @Override
    public Long getEmptyRoomCount() {
        return roomRepository.countEmptyRooms();
    }

    /**
     * Returns number of rooms that are occupied but not at full capacity.
     *
     * @return number of rooms that are occupied but not at full capacity
     */
    @Override
    public Long getHalfEmptyRoomCount() {
        return roomRepository.countHalfEmptyRooms();
    }

    /**
     * Returns number of full rooms.
     *
     * @return number of full rooms
     */
    @Override
    public Long getFullRoomCount() {
        return roomRepository.countFullRooms();
    }
}
