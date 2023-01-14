package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.BoardDTO;
import com.kb.ODA_Board.model.PageDTO;
import com.kb.ODA_Board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public String boardList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        PageDTO pageDTO = new PageDTO(boardService.getCount(), page);

        model.addAttribute("list", boardService.boardList(pageDTO));
        model.addAttribute("pageDTO", pageDTO);

        return "/board/boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer bno) {
        model.addAttribute("board", boardService.boardView(bno));

        return "/board/boardView";
    }

    @GetMapping("/board/modify/{bno}")
    public String boardModify(@PathVariable("bno") int bno, Model model) {
        model.addAttribute("board", boardService.boardView(bno));

        return "/board/boardModify";
    }

    @PutMapping("/board/modifyProc/{bno}")
    public String boardModifyPro(@PathVariable("bno") int bno, BoardDTO boardDTO, Model model) {
        boardDTO.setBno(bno);
        boardService.boardModify(boardDTO);

        model.addAttribute("board", boardService.boardView(bno));

        return "redirect:/board/view?bno=" + bno;
    }

    @DeleteMapping("/board/delete/{bno}")
    public String boardDeletePro(@PathVariable("bno") int bno) {
        boardService.boardDelete(bno);

        return "redirect:/board/list";
    }
}
