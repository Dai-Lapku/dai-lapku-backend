package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.SpeciesGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.SpeciesPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.repository.SpeciesRepository;

import java.util.List;

@RestController
@RequestMapping("/species")
public class SpeciesController {
    @Autowired
    private MapStructMapper mapper;
    @Autowired
    private SpeciesRepository speciesRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody SpeciesPostDto speciesPostDto) {
        speciesRepository.save(mapper.postDtoToSpecies(speciesPostDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpeciesGetDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.speciesToGetDto(speciesRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpeciesGetDto>> getAll() {
        return new ResponseEntity<>(
                mapper.speciesToDtoList(speciesRepository.findAll()),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody SpeciesPostDto speciesPostDto) {
        var species = speciesRepository.getReferenceById(id);
        mapper.updateSpeciesFromDto(speciesPostDto, species);
        speciesRepository.save(species);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id) {
        speciesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
