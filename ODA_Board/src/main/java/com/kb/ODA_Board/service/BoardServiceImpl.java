package com.kb.ODA_Board.service;

import com.kb.ODA_Board.mapper.BoardMapper;
import com.kb.ODA_Board.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public void boardWrite(BoardDTO boardDTO) {
        // 로그인한 유저의 아이디 가져오기
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        boardDTO.setAuthor(userId);
        boardMapper.boardWrite(boardDTO);
    }

    @Override
    public BoardDTO boardView(Integer bno) {
        return boardMapper.boardView(bno);
    }

    @Override
    public List<BoardDTO> boardList() {
        return boardMapper.boardList();
    }

    @Override
    public void boardModify(BoardDTO boardDTO) {
        boardMapper.boardModify(boardDTO);
    }

    @Override
    public void boardDelete(int bno) {
        boardMapper.boardDelete(bno);
    }
}
