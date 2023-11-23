package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private final String username;
    private final String password;

    public AuthenticationRequest(String login, String password) {
        this.username = login;
        this.password = password;
    }

}
