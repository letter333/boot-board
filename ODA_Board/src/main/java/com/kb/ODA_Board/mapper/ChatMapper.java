package com.kb.ODA_Board.mapper;

import com.kb.ODA_Board.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ChatMapper {
    void createRoom(Room room);
    List<Room> roomList();
    Room getRoom(String room_id);
    void deleteRoom(String room_id);
}
