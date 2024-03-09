package com.example.Presidential.elections.V1.Controller;

import com.example.Presidential.elections.V1.Entity.Candidate;
import com.example.Presidential.elections.V1.Services.CandidateService;
import com.example.Presidential.elections.V1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    public CandidateController() {}

    public CandidateController(CandidateService candidateService, UserService userService) {
        this.candidateService = candidateService;
        this.userService = userService;
    }

    @GetMapping("/registerCandidateTable")
    public String registerPage() {
        return "submitYourCandidacy";
    }

    @PostMapping("/registerCandidate")
    public String createCandidate(Candidate candidate, Model model) {
        if (candidateService.validateCandidate(candidate) == false) {
            return "candidateError";
        } else {
            candidateService.createCandidate(candidate);
            return "candidatePage";
        }
    }

    @GetMapping("/candidates")
    public String getCandidates(Model model) {
        List<Candidate> candidates = candidateService.getCandidates();
        model.addAttribute("candidates", candidates);
        return "candidatesList";
    }

    @GetMapping("/candidate")
    public String getCandidate(@RequestParam("id") Long id, Model model) {
        Candidate candidate = candidateService.getCandidate(id);
        model.addAttribute("candidate", candidate);
        return "candidatePage";
    }

    @GetMapping("/voting")
    public String voting(@RequestParam("id") Long id, Model model) {
        List<Candidate> candidates = candidateService.rankingOfCandidates();
        model.addAttribute("candidates", candidates);
        if (userService.userHasVoted() == true) {
            Candidate candidate = candidateService.getCandidate(id);
            candidateService.candidateWasVoted(candidate);
            candidateService.rankingOfCandidates();
        }
        return "homeUser";
    }

    @GetMapping("/myCandudatePage")
    public String myCandudatePage(@RequestParam("email") String email, Model model) {
        Candidate candidate = candidateService.getMyCandidate(email);
        model.addAttribute("candidate", candidate);
        return "candidatePage";
    }

}
