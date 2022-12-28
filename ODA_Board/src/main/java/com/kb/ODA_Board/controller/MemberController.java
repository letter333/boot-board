package com.kb.ODA_Board.controller;

import com.kb.ODA_Board.repository.dto.MemberDTO;
import com.kb.ODA_Board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/getId")
    public String getId(MemberDTO dto) {
        boolean b = service.getId(dto);
        if (b) {
            return "no";
        }

        return "ok";

    }

    @GetMapping("/getId")
    public ModelAndView test(MemberDTO dto) {
        ModelAndView mav = new ModelAndView("member/register");
        return mav;

    }

}