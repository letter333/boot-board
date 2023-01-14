package com.kb.ODA_Board.mapper;

import com.kb.ODA_Board.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface BoardMapper {
    void boardWrite(BoardDTO boardDTO);
    BoardDTO boardView(Integer bno);
    List<BoardDTO> boardList();
    void boardModify(BoardDTO boardDTO);
    void boardDelete(int bno);
}
