package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.BoardDTO;
import com.kb.ODA_Board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardServiceImpl boardService;

    @GetMapping("/")
    public String mainForm() {
        return "main";
    }

    @GetMapping("/board/write")
    public String writeForm() {
        return "/board/writeForm";
    }

    @PostMapping("/board/writeProc")
    public String writeFormPro(BoardDTO boardDTO) {
        boardService.boardWrite(boardDTO);

        return "redirect:/";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.boardList());

        return "/board/boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer bno) {
        model.addAttribute("board", boardService.boardView(bno));

        return "/board/boardView";
    }
}
