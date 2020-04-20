package com.soft.srms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public String showHome() {
        return "home";
    }
    
    @GetMapping("/old-dorm")
    public String showOldDorm() {
        return "old_dorm";
    }
    
    @GetMapping("/new-dorm")
    public String showNewDorm() {
        return "new_dorm";
    }

}
