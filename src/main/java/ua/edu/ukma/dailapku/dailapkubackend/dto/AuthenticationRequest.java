package ua.edu.ukma.dailapku.dailapkubackend.dto;

public class AuthenticationRequest {
    private final String login;
    private final String password;

    public AuthenticationRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
