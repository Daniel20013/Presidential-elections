package com.example.Presidential.elections.V1.Services;

import com.example.Presidential.elections.V1.Entity.Candidate;

import java.util.List;

public interface ICandidateService {
    boolean validateCandidate(Candidate candidate);

    void createCandidate(Candidate candidate);

    List getCandidates();

    Candidate getCandidate(Long id);

    Candidate getMyCandidate(String email);

    void candidateWasVoted(Candidate candidate);

    List<Candidate> rankingOfCandidates();

    void deleteCandidates();

    void topTwoCandidates();
}
