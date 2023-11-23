package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AuthenticationRequest;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AuthenticationResponse;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.model.Role;
import ua.edu.ukma.dailapku.dailapkubackend.repository.UserRepository;
import ua.edu.ukma.dailapku.dailapkubackend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {
    private final MapStructMapper mapper;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticateController(AuthService authService, MapStructMapper mapper, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> create(@Valid @RequestBody UserPostDto userPostDto) {
        var user = mapper.postDtoToUser(userPostDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
