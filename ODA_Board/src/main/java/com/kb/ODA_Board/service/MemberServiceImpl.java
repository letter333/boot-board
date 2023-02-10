package com.kb.ODA_Board.service;

import com.kb.ODA_Board.model.MemberDTO;
import com.kb.ODA_Board.mapper.MemberMapper;
import com.kb.ODA_Board.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {
    private final MemberMapper memberMapper;

    @Override
    public int createMember(MemberDTO dto) {
        int result = memberMapper.createMember(dto);

        return result;
    }

    @Override
    public int loginMember(String id) {
        Optional<MemberDTO> result = memberMapper.loginMember(id);
        return 0;
    }

    @Override
    public int idCheck(String id) {
        return memberMapper.idCheck(id);
    }

    @Override
    public MemberDTO getMember(String id) {
        return memberMapper.getMember(id);
    }

    @Override
    public void modifyMember(MemberDTO memberDTO) {
        memberMapper.modifyMember(memberDTO);
    }

    @Override
    public void resetPassword(Map map) {
        memberMapper.resetPassword(map);
    }


    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.loginMember(id).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 사용자입니다."));

        if(memberDTO != null) {
            return new User(memberDTO);
        }

        return null;
    }
}
