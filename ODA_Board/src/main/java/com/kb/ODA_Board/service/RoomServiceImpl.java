package com.kb.ODA_Board.service;

import com.kb.ODA_Board.mapper.ChatMapper;
import com.kb.ODA_Board.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final ChatMapper chatMapper;

    @Override
    public Room createRoom(String name) {
        Room room = Room.create(name);
        chatMapper.createRoom(room);

        return room;
    }

    @Override
    public List<Room> roomList() {
        return chatMapper.roomList();
    }

    @Override
    public Room getRoom(String room_id) {
        return chatMapper.getRoom(room_id);
    }

    @Override
    public void deleteRoom(String room_id) {
        chatMapper.deleteRoom(room_id);
    }
}
