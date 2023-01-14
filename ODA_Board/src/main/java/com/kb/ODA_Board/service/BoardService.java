package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.BoardDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    void boardWrite(BoardDTO boardDTO);
    BoardDTO boardView(Integer bno);
    List<BoardDTO> boardList();
    void boardModify(BoardDTO boardDTO);
    void boardDelete(int bno);
}
