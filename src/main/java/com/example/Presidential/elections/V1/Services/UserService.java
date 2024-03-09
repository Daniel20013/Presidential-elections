package com.example.Presidential.elections.V1.Services;

import com.example.Presidential.elections.V1.Entity.User;
import com.example.Presidential.elections.V1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private User currentUser = null;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            currentUser = user;
            currentUser.setPassword(null);
            return currentUser;
        }
        return null;
    }
    @Override
    public void createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        User userNotPass = user;
        userNotPass.setPassword(null);
        return user;
    }

    @Override
    public boolean userHasVoted() {
        if (currentUser.getVote() == true) {
            User user = userRepository.findById(currentUser.getId()).orElse(null);
            user.setVote(false);
            userRepository.save(user);
            currentUser.setVote(false);
            return true;
        }
        return false;
    }

}