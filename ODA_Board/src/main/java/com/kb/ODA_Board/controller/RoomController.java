package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.service.RoomRepository;
import com.kb.ODA_Board.service.RoomService;
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
        rttr.addFlashAttribute("roomName", roomService.createRoom(name));

        return "redirect:/chat/rooms";
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

        return "redirect:/chat/rooms";
    }
}
