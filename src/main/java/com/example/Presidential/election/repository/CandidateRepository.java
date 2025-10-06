package com.example.Presidential.election.repository;

import com.example.Presidential.election.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepository extends JpaRepository <Candidate, Long> {
    Optional<Candidate> findByUsername(String username);
}
