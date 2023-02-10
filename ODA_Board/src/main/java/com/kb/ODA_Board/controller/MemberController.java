package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.model.MemberDTO;
import com.kb.ODA_Board.service.MailServiceImpl;
import com.kb.ODA_Board.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImpl memberService;
    private final MailServiceImpl mailService;
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
        memberService.createMember(dto);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String memberLoginForm(HttpServletRequest request,
                                  @RequestParam(value="error", required = false) String error,
                                  @RequestParam(value = "exception", required = false) String exception,
                                  Model model) {
        // 이전 페이지로 되돌아가기 위한 Referer 헤더값을 세션의 prevPage attribute로 저장
        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/member/loginForm";
    }

    @ResponseBody
    @PostMapping("/create/idCheck")
    public int memberCheck(String id) {
        return memberService.idCheck(id);
    }

    @ResponseBody
    @PostMapping("/create/emailCheck")
    public String memberEmailCheck(String email) throws Exception {
        String type = "createMember";
        String code = mailService.sendSimpleMessage(email, type);

        return code;
    }

    @GetMapping("/detail/{id}")
    public String memberDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("member", memberService.getMember(id));

        return "/member/memberDetail";
    }

    @GetMapping("/modify/{id}")
    public String memberModify(@PathVariable("id") String id, Model model) {
        model.addAttribute("member", memberService.getMember(id));

        return "/member/memberModify";
    }

    @PostMapping("/modifyProc/{id}")
    public String memberModifyProc(@PathVariable("id") String id, MemberDTO memberDTO) {
        memberService.modifyMember(memberDTO);

        return "redirect:/member/detail/" + id;
    }

    @GetMapping("/findPassword")
    public String findPassword() {
        return "/member/findPassword";
    }

    @PostMapping("/findPasswordProc")
    public String findPasswordProc(MemberDTO memberDTO) throws Exception {
        String type = "findPassword";
        String code = mailService.sendSimpleMessage(memberDTO.getEmail(), type);

        Map<String, Object> map = new HashMap<>();
        map.put("pw", passwordEncoder.encode(code));
        map.put("id", memberDTO.getId());

        memberService.resetPassword(map);

        return "redirect:/member/login";
    }
}