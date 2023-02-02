package com.kb.ODA_Board.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class Room {
    private String room_id;
    private String name;
    private String master;
    private Set<WebSocketSession> sessions = new HashSet<>();

    public static Room create(String name) {
        Room room = new Room();
        String master = SecurityContextHolder.getContext().getAuthentication().getName();

        room.room_id = UUID.randomUUID().toString();
        room.name = name;
        room.master = master;
        return room;
    }
}
