package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.service.RoomRepository;
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
    private final RoomRepository roomRepository;

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        model.addAttribute("list", roomRepository.findAllRooms());

        return "/chat/roomList";
    }

    @PostMapping("/room")
    public String createRoom(@RequestParam String name, RedirectAttributes rttr) {
        rttr.addFlashAttribute("roomName", roomRepository.createRoomDTO(name));

        return "redirect:/chat/rooms";
    }

    @GetMapping("/room")
    public void getRoom(String roomId, Model model) {
        model.addAttribute("room", roomRepository.findRoomById(roomId));
    }
}
