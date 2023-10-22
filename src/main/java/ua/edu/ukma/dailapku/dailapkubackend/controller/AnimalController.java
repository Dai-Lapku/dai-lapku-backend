package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AnimalGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AnimalPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.repository.AnimalRepository;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    private MapStructMapper mapper;
    @Autowired
    private AnimalRepository animalRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody AnimalPostDto animalPostDto) {
        animalRepository.save(mapper.postDtoToAnimal(animalPostDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalGetDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.animalToGetDto(animalRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalGetDto>> getAll() {
        return new ResponseEntity<>(
                mapper.animalsToDtoList(animalRepository.findAll()),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody AnimalPostDto animalPostDto) {
        var shelter = animalRepository.getReferenceById(id);
        mapper.updateShelterFromDto(animalPostDto, shelter);
        animalRepository.save(shelter);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id) {
        animalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
