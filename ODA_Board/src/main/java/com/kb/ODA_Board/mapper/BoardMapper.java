package com.kb.ODA_Board.mapper;

import com.kb.ODA_Board.model.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {
    void boardWrite(BoardDTO boardDTO);
}
