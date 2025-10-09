package com.example.Presidential.election.repository;

import com.example.Presidential.election.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.vote = :status")
    void updateVotingStatusForAll(@Param("status") boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.candidate = :status")
    void updateCandidateStatusForAll(@Param("status") boolean status);

}
