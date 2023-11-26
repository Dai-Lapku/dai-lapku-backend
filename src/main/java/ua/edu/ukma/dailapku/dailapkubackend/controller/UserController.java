package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.model.Role;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;
import ua.edu.ukma.dailapku.dailapkubackend.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MapStructMapper mapper;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody UserPostDto userPostDto) {
        var user = mapper.postDtoToUser(userPostDto);
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserGetDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.userToGetDto(userRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserGetDto>> getAll() {
        return new ResponseEntity<>(
                mapper.usersToDtoList(userRepository.findAll()),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody UserPostDto userPostDto) {
        var user = userRepository.getReferenceById(id);
        mapper.updateUserFromDto(userPostDto, user);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<Void> blockUser(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        user.setEnabled(false);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
