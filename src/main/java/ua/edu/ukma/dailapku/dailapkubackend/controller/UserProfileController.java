package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserProfileGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserProfilePostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.repository.UserProfileRepository;

import java.util.List;

@RestController
@RequestMapping("/user-profile")
public class UserProfileController {

    @Autowired
    private MapStructMapper mapper;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody UserProfilePostDto userProfilePostDto) {
        var userProfile = mapper.userProfilePostDtoToEntity(userProfilePostDto);
        userProfileRepository.save(userProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileGetDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.userProfileToGetDto(userProfileRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserProfileGetDto>> getAll() {
        return new ResponseEntity<>(
                mapper.userProfilesToGetDtoList(userProfileRepository.findAll()),
                HttpStatus.OK
        );
    }



    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody UserProfilePostDto userProfilePostDto) {
        var userProfile = userProfileRepository.getReferenceById(id);
        mapper.updateUserProfileFromDto(userProfilePostDto, userProfile);
        userProfileRepository.save(userProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        userProfileRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



