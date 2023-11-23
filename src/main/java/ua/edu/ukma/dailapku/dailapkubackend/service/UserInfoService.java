package ua.edu.ukma.dailapku.dailapkubackend.service;

import org.springframework.stereotype.Service;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;
import ua.edu.ukma.dailapku.dailapkubackend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserInfoService {

    private final UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
