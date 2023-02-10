package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enter(MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("user", messageDTO.getWriter());
        headerAccessor.getSessionAttributes().put("room_id", messageDTO.getRoom_id());

        messageDTO.setMessage(messageDTO.getWriter() + " 님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + messageDTO.getRoom_id(), messageDTO);
    }

    @MessageMapping(value = "/chat/message")
    public void message(MessageDTO messageDTO) {
        template.convertAndSend("/sub/chat/room/" + messageDTO.getRoom_id(), messageDTO);
    }

    @EventListener
    public void webSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String user = (String) headerAccessor.getSessionAttributes().get("user");
        String roomId = (String) headerAccessor.getSessionAttributes().get("room_id");

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setWriter(user);
        messageDTO.setMessage(user + "님이 퇴장하셨습니다.");

        template.convertAndSend("/sub/chat/room/" + roomId, messageDTO);
    }
}
