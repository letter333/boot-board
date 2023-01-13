package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.BoardDTO;

import java.util.List;

public interface BoardService {
    void boardWrite(BoardDTO boardDTO);
    BoardDTO boardView(Integer bno);
    List<BoardDTO> boardList();
}
