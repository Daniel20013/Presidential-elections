package com.example.Presidential.elections.V1.Services;

import com.example.Presidential.elections.V1.Entity.Candidate;
import com.example.Presidential.elections.V1.Repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateRepository getCandidateRepository() {
        return candidateRepository;
    }

    @Override
    public boolean validateCandidate(Candidate candidate) {
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

    @Override
    public void candidateWasVoted(Candidate candidate) {
        int nrOfVotes = candidate.getNrOfVotes();
        ++nrOfVotes;
        candidate.setNrOfVotes(nrOfVotes);
        candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> rankingOfCandidates() {
        return candidateRepository.findAll(Sort.by(Sort.Direction.DESC, "nrOfVotes"));
    }

    @Override
    public void deleteCandidates() {
        candidateRepository.deleteAll();
    }

    @Override
    public void topTwoCandidates() {
        List<Candidate> allCandidates = candidateRepository.findAll(Sort.by(Sort.Direction.DESC, "nrOfVotes"));
        List<Candidate> candidates = allCandidates.subList(0, Math.min(2, allCandidates.size()));
        candidateRepository.deleteAll();
        for (Candidate candidate : candidates) {
            candidate.setNrOfVotes(0);
            candidateRepository.save(candidate);
        }
    }

}
