package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.Room;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class RoomRepository {
    private Map<String, Room> roomMap;

    @PostConstruct
    private void init() {
        roomMap = new LinkedHashMap<>();
    }

    public List<Room> findAllRooms() {
        List<Room> result = new ArrayList<>(roomMap.values());
        Collections.reverse(result);

        return result;
    }

    public Room findRoomById(String id) {
        return roomMap.get(id);
    }

    public Room createRoomDTO(String name) {
        Room room = Room.create(name);
        roomMap.put(room.getRoom_id(), room);

        return room;
    }
}
