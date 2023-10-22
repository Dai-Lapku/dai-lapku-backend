package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.ShelterGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.ShelterPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.repository.ShelterRepository;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    private MapStructMapper mapper;
    @Autowired
    private ShelterRepository shelterRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody ShelterPostDto shelterPostDto) {
        shelterRepository.save(mapper.postDtoToShelter(shelterPostDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShelterGetDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.shelterToGetDto(shelterRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ShelterGetDto>> getAll() {
        return new ResponseEntity<>(
                mapper.sheltersToDtoList(shelterRepository.findAll()),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody ShelterPostDto shelterPostDto) {
        var shelter = shelterRepository.getReferenceById(id);
        mapper.updateShelterFromDto(shelterPostDto, shelter);
        shelterRepository.save(shelter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id) {
        shelterRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
