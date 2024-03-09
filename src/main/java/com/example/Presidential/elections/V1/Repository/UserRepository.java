package com.example.Presidential.elections.V1.Repository;

import com.example.Presidential.elections.V1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
