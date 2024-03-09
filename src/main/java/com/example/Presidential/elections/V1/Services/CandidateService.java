package com.example.Presidential.elections.V1.Services;

import com.example.Presidential.elections.V1.Entity.Candidate;
import com.example.Presidential.elections.V1.Repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateRepository getCandidateRepository() {
        return candidateRepository;
    }

    public boolean validateCandidate (Candidate candidate) {
        int minAge = 35;
        if (candidate.getAge() < minAge) {
            return false;
        } else if (!candidate.getCitizenship().equalsIgnoreCase("Romana") && !candidate.getCitizenship().equals("romana")) {
            return false;
        }
        return true;
    }

    @Override
    public void createCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    @Override
    public List getCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id).orElse(null);
        return candidate;
    }

    @Override
    public Candidate getMyCandidate(String email) {
        return candidateRepository.findByEmail(email);
    }

    public void candidateWasVoted(Candidate candidate) {
        int nrOfVotes = candidate.getNrOfVotes();
        ++nrOfVotes;
        candidate.setNrOfVotes(nrOfVotes);
        candidateRepository.save(candidate);
    }

    public List<Candidate> rankingOfCandidates() {
        return candidateRepository.findAll(Sort.by(Sort.Direction.DESC, "nrOfVotes"));
    }



}
