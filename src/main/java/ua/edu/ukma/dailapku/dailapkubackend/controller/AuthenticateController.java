package ua.edu.ukma.dailapku.dailapkubackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AuthenticationRequest;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AuthenticationResponse;
import ua.edu.ukma.dailapku.dailapkubackend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    private final AuthService authService;

    @Autowired
    public AuthenticateController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
