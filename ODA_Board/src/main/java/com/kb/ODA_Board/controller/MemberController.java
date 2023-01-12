package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.service.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImpl service;

    // 생성자 주입을 사용해 의존성 주입
    public MemberController(MemberServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String createMemberForm() {
        return "/member/registerForm";
    }

    @PostMapping("/create")
    public String createMemberPro() {
        return "redirect:/";
    }

    @GetMapping("/login")
    public String memberLoginForm() {
        return "/member/loginForm";
    }
}