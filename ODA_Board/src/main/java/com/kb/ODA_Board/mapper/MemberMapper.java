package com.kb.ODA_Board.mapper;

import com.kb.ODA_Board.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@Mapper
public interface MemberMapper {
    int createMember(MemberDTO dto);
    Optional<MemberDTO> loginMember(String id);
}
