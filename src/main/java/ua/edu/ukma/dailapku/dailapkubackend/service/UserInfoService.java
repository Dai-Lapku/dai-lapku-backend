package ua.edu.ukma.dailapku.dailapkubackend.service;

import org.springframework.stereotype.Service;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;

import java.util.Optional;

@Service
public class UserInfoService {


    public Optional<User> getUserByLogin(String login) {
        return Optional.empty();
    }
}
