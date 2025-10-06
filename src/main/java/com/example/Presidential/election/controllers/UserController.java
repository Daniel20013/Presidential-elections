package com.example.Presidential.election.controllers;

import com.example.Presidential.election.DTO.UserDTO;
import com.example.Presidential.election.model.User;
import com.example.Presidential.election.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String userPage(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("User not found"));
        UserDTO userDTO = new UserDTO(user);
        model.addAttribute("user", userDTO);
        return "userPage";
    }

}
