package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.BoardDTO;
import com.kb.ODA_Board.model.CommentDTO;
import com.kb.ODA_Board.model.PageDTO;
import com.kb.ODA_Board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
        return "/board/boardWrite";
    }

    @ResponseBody
    @PostMapping("/board/writeProc")
    public String writeFormPro(BoardDTO boardDTO) {
        boardService.boardWrite(boardDTO);

        return "/board/list";
    }

    @GetMapping("/board/list")
    public String boardList(Model model,
                            @RequestParam(value = "page", defaultValue = "1") int page,
                            @RequestParam(required = false, value = "searchType") String searchType,
                            @RequestParam(required = false, value = "keyword") String keyword) {
        PageDTO pageDTO = new PageDTO(boardService.getCount(searchType, keyword), page, searchType, keyword);

        model.addAttribute("list", boardService.boardList(pageDTO));
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageDTO", pageDTO);

        return "/board/boardList";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer bno) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        model.addAttribute("board", boardService.boardView(bno));
        model.addAttribute("commentList", boardService.commentList(bno));
        model.addAttribute("memberId", memberId);

        return "/board/boardView";
    }

    @GetMapping("/board/modify/{bno}")
    public String boardModify(@PathVariable("bno") int bno, Model model) {
        model.addAttribute("board", boardService.boardView(bno));

        return "/board/boardModify";
    }

    @ResponseBody
    @PutMapping("/board/modifyProc/{bno}")
    public String boardModifyPro(@PathVariable("bno") int bno, BoardDTO boardDTO, Model model) {
        boardDTO.setBno(bno);
        boardService.boardModify(boardDTO);

        model.addAttribute("board", boardService.boardView(bno));

        return "/board/view?bno=" + bno;
    }

    @DeleteMapping("/board/delete/{bno}")
    public String boardDeletePro(@PathVariable("bno") int bno) {
        boardService.boardDelete(bno);

        return "redirect:/board/list";
    }

    @PostMapping("/board/comment/write/{bno}")
    public String commentWrite(@PathVariable("bno") int bno, CommentDTO commentDTO) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        commentDTO.setComment_bno(bno);
        commentDTO.setMember_id(memberId);
        boardService.commentWrite(commentDTO);

        return "redirect:/board/view?bno=" + bno;
    }

    @DeleteMapping("/board/comment/delete/{cno}")
    public String commentDelete(@PathVariable("cno") int cno, int bno) {
        boardService.commentDelete(cno);

        return "redirect:/board/view?bno=" + bno;
    }

}
