package com.kb.ODA_Board.repository.dao;

import com.kb.ODA_Board.repository.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {
    @Select("SELECT IFNULL(COUNT(*), 0) FROM MEMBER_INFO WHERE ID = #{id}")
    int getId(MemberDTO dto);
}
