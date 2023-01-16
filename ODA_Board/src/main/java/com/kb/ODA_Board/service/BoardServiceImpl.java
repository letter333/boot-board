package com.kb.ODA_Board.service;

import com.kb.ODA_Board.mapper.BoardMapper;
import com.kb.ODA_Board.model.BoardDTO;
import com.kb.ODA_Board.model.CommentDTO;
import com.kb.ODA_Board.model.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    // bno에 해당하는 게시글 보기
    @Override
    public BoardDTO boardView(Integer bno) {
        return boardMapper.boardView(bno);
    }

    // 페이지 번호에 해당하는 게시글 리스트
    @Override
    public List<BoardDTO> boardList(PageDTO pageDTO) {
        return boardMapper.boardList(pageDTO);
    }

    @Override
    public void boardModify(BoardDTO boardDTO) {
        boardMapper.boardModify(boardDTO);
    }

    @Override
    public void boardDelete(int bno) {
        boardMapper.boardDelete(bno);
    }

    @Override
    public int getCount() {
        return boardMapper.getCount();
    }

    @Override
    public void commentWrite(CommentDTO commentDTO) {
        boardMapper.commentWrite(commentDTO);
    }

    @Override
    public List<CommentDTO> commentList(int bno) {
        return boardMapper.commentList(bno);
    }

    @Override
    public void commentDelete(int cno) {
        boardMapper.commentDelete(cno);
    }
}
