package com.kb.ODA_Board.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private String room_id;
    private String writer;
    private String message;
}
