package com.example.Presidential.election.controllers;

import com.example.Presidential.election.repository.CandidateRepository;
import com.example.Presidential.election.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/")
    public String admin() {
        return "adminPage";
    }

    @PostMapping("/startVoting")
    public String startVoting() {
        userRepository.updateVotingStatusForAll(true);
        return "adminPage";
    }

    @PostMapping("/stopVoting")
    public String stopVoting() {
        userRepository.updateVotingStatusForAll(false);
        return "adminPage";
    }

    @DeleteMapping("/deleteAllCadidates")
    public String deleteAllCandidates() {
        candidateRepository.deleteAllCandidatesFast();
        userRepository.updateCandidateStatusForAll(false);
        return "adminPage";
    }

}
