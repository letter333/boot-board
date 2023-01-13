package com.kb.ODA_Board.service;

import com.kb.ODA_Board.mapper.BoardMapper;
import com.kb.ODA_Board.model.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
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
}
