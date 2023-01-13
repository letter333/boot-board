package com.kb.ODA_Board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class    BoardController {
    @GetMapping("/")
    public String mainForm() {
        return "main";
    }
}
