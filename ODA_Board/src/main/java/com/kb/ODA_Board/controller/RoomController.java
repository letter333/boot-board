package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.Room;
import com.kb.ODA_Board.service.RoomServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomController {
    private final RoomServiceImpl roomService;

    // 채팅방 리스트
    @GetMapping("/list")
    public String getRooms(Model model) {
        model.addAttribute("list", roomService.roomList());

        return "/chat/roomList";
    }

    // 채팅방 생성
    @PostMapping("/room")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
        Room room = roomService.createRoom(name);

        rttr.addFlashAttribute("roomName", room);

        return "redirect:/chat/room?room_id=" + room.getRoom_id();
    }

    // 채팅방 화면
    @GetMapping("/room")
    public String getRoom(String room_id, Model model) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("memberId", memberId);
        model.addAttribute("room", roomService.getRoom(room_id));

        return "/chat/room";
    }

    @DeleteMapping("/room/delete/{room_id}")
    public String deleteRoom(@PathVariable String room_id) {
        roomService.deleteRoom(room_id);

        return "redirect:/chat/list";
    }
}
