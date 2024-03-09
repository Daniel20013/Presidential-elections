package com.example.Presidential.elections.V1.Controller;

import com.example.Presidential.elections.V1.Entity.Candidate;
import com.example.Presidential.elections.V1.Entity.User;
import com.example.Presidential.elections.V1.Services.CandidateService;
import com.example.Presidential.elections.V1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    private User currentUser = null;

    @Autowired
    private UserService userService;

    @Autowired
    private CandidateService candidateService;

    public UserController(UserService userServices, CandidateService candidateService) {
        this.userService = userServices;
        this.candidateService = candidateService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Candidate> candidates = candidateService.rankingOfCandidates();
        model.addAttribute("candidates", candidates);
        return "home";
    }

    @GetMapping("/firstPage")
    public String firstPage(User user, Model model) {
        List<Candidate> candidates = candidateService.rankingOfCandidates();
        model.addAttribute("candidates", candidates);
        model.addAttribute("user", user);
        return "userFirstPage";
    }

    @GetMapping("/home")
    public String goHome(Model model) {
        List<Candidate> candidates = candidateService.rankingOfCandidates();
        model.addAttribute("candidates", candidates);
        model.addAttribute("user", currentUser);
        return "homeUser";
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "registerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        userService.createUser(user);
        model.addAttribute("user", user);
        return "home";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        User user = userService.authenticate(email, password);
        if (user != null) {
            model.addAttribute("user", user);
            currentUser = user;
            currentUser.setPassword(null);
            return "userFirstPage";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "errorPage";
        }
    }

    @GetMapping("/userPage")
    public String userPage(Model model) {
        model.addAttribute("user", currentUser);
        return "userPage";
    }

    @GetMapping("/myCandudatePage")
    public String myCandudatePage(Model model) {
        Candidate candidate = candidateService.getMyCandidate(currentUser.getEmail());
        model.addAttribute("candidate", candidate);
        return "candidatePage";
    }
    
}
