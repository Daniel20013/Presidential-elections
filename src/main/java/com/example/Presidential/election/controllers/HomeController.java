package com.example.Presidential.election.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class HomeController {

    @GetMapping("/home")
    public String genericHome(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "home";
    }

}
