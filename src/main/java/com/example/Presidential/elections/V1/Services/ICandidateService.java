package com.example.Presidential.elections.V1.Services;

import com.example.Presidential.elections.V1.Entity.Candidate;

import java.util.List;

public interface ICandidateService {
    void createCandidate(Candidate candidate);

    List getCandidates();

    Candidate getCandidate(Long id);
}
