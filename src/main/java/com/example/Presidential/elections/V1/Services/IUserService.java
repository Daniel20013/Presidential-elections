package com.example.Presidential.elections.V1.Services;

import com.example.Presidential.elections.V1.Entity.User;

public interface IUserService {
    User authenticate(String email, String password);

    void createUser(User user);

    User getUserById(Long id);

    boolean userHasVoted();
}
