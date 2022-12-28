package com.kb.ODA_Board.service;

import com.kb.ODA_Board.repository.dao.MemberDAO;
import com.kb.ODA_Board.repository.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberDAO dao;

    public boolean getId(MemberDTO dto) {
        int n = dao.getId(dto);
        return n > 0;
    }
}
