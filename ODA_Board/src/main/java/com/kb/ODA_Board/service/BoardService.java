package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.BoardDTO;
import com.kb.ODA_Board.model.CommentDTO;
import com.kb.ODA_Board.model.PageDTO;

import java.util.List;
import java.util.Map;

public interface BoardService {
    void boardWrite(BoardDTO boardDTO);
    BoardDTO boardView(Integer bno);
    List<BoardDTO> boardList(PageDTO pageDTO);
    void boardModify(BoardDTO boardDTO);
    void boardDelete(int bno);
    int getCount(String searchType, String keyword);
    void commentWrite(CommentDTO commentDTO);
    List<CommentDTO> commentList(int bno);
    void commentDelete(int cno);
    void commentModify(Map map);
}
