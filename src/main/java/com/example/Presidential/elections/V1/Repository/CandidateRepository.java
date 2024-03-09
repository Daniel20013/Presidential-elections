package com.example.Presidential.elections.V1.Repository;

import com.example.Presidential.elections.V1.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);
}