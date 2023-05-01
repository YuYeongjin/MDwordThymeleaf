package com.example.MDwordThymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {

    @RequestMapping("")
    public String home() {
        return "redirect:/title";
    }

    @GetMapping("title")
    public void title(){}

}
