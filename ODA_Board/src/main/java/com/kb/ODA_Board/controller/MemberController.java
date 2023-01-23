package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.MemberDTO;
import com.kb.ODA_Board.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public String memberLoginForm(HttpServletRequest request) {
        // 이전 페이지로 되돌아가기 위한 Referer 헤더값을 세션의 prevPage attribute로 저장
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }

        return "/member/loginForm";
    }

    @ResponseBody
    @PostMapping("/create/idCheck")
    public int memberCheck(String id) {
        return service.idCheck(id);
    }
}