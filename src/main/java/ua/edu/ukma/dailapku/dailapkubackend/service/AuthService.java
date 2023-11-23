package ua.edu.ukma.dailapku.dailapkubackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AuthenticationRequest;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AuthenticationResponse;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserInfoService userInfoService;

    @Autowired
    public AuthService(
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            UserInfoService userInfoService
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userInfoService = userInfoService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        User user = userInfoService.getUserByUsername(authRequest.getUsername());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public User getUserPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
