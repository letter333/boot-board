package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.service.RoomRepository;
import com.kb.ODA_Board.service.RoomService;
import com.kb.ODA_Board.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {
    private final RoomServiceImpl roomService;

    // 채팅방 리스트
    @GetMapping("/rooms")
    public String getRooms(Model model) {
        model.addAttribute("list", roomService.roomList());

        return "/chat/roomList";
    }

    // 채팅방 생성
    @PostMapping("/room")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
        rttr.addFlashAttribute("roomName", roomService.createRoom(name));

        return "redirect:/chat/rooms";
    }

    // 채팅방 화면
    @GetMapping("/room")
    public void getRoom(String room_id, Model model) {
        model.addAttribute("room", roomService.getRoom(room_id));
    }
}
