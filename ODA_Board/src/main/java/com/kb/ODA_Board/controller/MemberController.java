package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.MemberDTO;
import com.kb.ODA_Board.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImpl service;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/create")
    public String createMemberForm() {
        return "/member/registerForm";
    }

    @PostMapping("/create")
    public String createMemberPro(MemberDTO dto) {
        String rawPw = dto.getPw();
        String encPw = passwordEncoder.encode(rawPw);
        dto.setPw(encPw);
        service.createMember(dto);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String memberLoginForm() {
        return "/member/loginForm";
    }
}