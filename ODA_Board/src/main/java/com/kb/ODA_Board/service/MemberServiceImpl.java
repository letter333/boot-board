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
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        MemberDTO memberDTO = memberMapper.loginMember(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        if(memberDTO != null) {
            return new User(memberDTO);
        }

        return null;
    }
}
