package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.MemberDTO;

import java.util.Map;

public interface MemberService {
    int createMember(MemberDTO dto);
    int loginMember(String id);
    int idCheck(String id);
    MemberDTO getMember(String id);
    void modifyMember(MemberDTO memberDTO);
}
