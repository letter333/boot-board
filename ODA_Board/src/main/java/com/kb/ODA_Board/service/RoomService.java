package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(String name);
    List<Room> roomList();
    Room getRoom(String room_id);
    void deleteRoom(String room_id);
}
