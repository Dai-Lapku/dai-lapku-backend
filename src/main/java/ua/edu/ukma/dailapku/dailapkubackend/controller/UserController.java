package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.model.Role;
import ua.edu.ukma.dailapku.dailapkubackend.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private MapStructMapper mapstructMapper;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserPostDto userPostDto) {
        var user = mapstructMapper.postDtoToUser(userPostDto);
        user.setRole(Role.ADMIN);
        System.out.println("Saving user..." + user);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getById(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(
                mapstructMapper.userToGetDto(userRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<UserGetDto>> getAll() {
        return new ResponseEntity<>(
                userRepository.findAll().stream().map(mapstructMapper::userToGetDto).toList(),
                HttpStatus.OK
        );
    }


}
