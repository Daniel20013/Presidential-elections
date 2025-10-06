package com.example.Presidential.election.DTO;

import com.example.Presidential.election.model.User;

public class UserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String shortDescription;
    private Boolean vote;
    private Boolean candidate;

    public String getUsername() {
        return username;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dateOfBirth = user.getDateOfBirth();
        this.shortDescription = user.getShortDescription();
        this.vote = user.getVote();
        this.candidate = user.getCandidate();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public Boolean getVote() {
        return vote;
    }

    public Boolean getCandidate() {
        return candidate;
    }
}
