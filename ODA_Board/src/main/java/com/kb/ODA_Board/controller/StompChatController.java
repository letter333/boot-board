package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enter(MessageDTO messageDTO) {
        messageDTO.setMessage(messageDTO.getWriter() + " 님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + messageDTO.getRoom_id(), messageDTO);
    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageDTO messageDTO) {
        template.convertAndSend("/sub/chat/room/" + messageDTO.getRoom_id(), messageDTO);
    }
}
