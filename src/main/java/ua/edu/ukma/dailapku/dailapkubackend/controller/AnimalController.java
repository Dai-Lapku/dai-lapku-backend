package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AnimalGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AnimalPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.model.Animal;
import ua.edu.ukma.dailapku.dailapkubackend.model.Sex;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;
import ua.edu.ukma.dailapku.dailapkubackend.model.UserLike;
import ua.edu.ukma.dailapku.dailapkubackend.repository.AnimalRepository;
import ua.edu.ukma.dailapku.dailapkubackend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    @Autowired
    private MapStructMapper mapper;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

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

    @GetMapping(value = "/shelter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalGetDto>> getAllByShelterId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.animalsToDtoList(animalRepository.findAllByShelterId(id)),
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

    @GetMapping("/filter")
    public ResponseEntity<List<AnimalGetDto>> filterAnimals(
            @RequestParam(value = "sex", required = false) Sex sex,
            @RequestParam(value = "speciesName", required = false) String speciesName,
            @RequestParam(value = "shelterId", required = false) Long shelterId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
        Root<Animal> root = cq.from(Animal.class);

        List<Predicate> predicates = new ArrayList<>();

        if (sex != null) {
            predicates.add(cb.equal(root.get("sex"), sex));
        }
        if (speciesName != null) {
            root.join("species", JoinType.INNER);
            predicates.add(cb.equal(root.get("species").get("name"), speciesName));
        }
        if (shelterId != null) {
            predicates.add(cb.equal(root.get("shelter").get("id"), shelterId));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        List<Animal> filteredAnimals = entityManager.createQuery(cq).getResultList();
        return new ResponseEntity<>(mapper.animalsToDtoList(filteredAnimals), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<Void> likeAnimal(@RequestParam Long userId, @RequestParam Long animalId) {
        User user = userRepository.getReferenceById(userId);
        Animal animal = animalRepository.getReferenceById(animalId);

        UserLike userLike = UserLike.builder()
                .user(user)
                .likedAnimal(animal)
                .build();

        user.getLikedAnimals().add(userLike);
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/like")
    public ResponseEntity<Void> removeLike(@RequestParam Long userId, @RequestParam Long animalId) {
        User user = userRepository.getReferenceById(userId);
        Animal animal = animalRepository.getReferenceById(animalId);

        user.getLikedAnimals().removeIf(like -> like.getLikedAnimal().equals(animal));
        userRepository.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/liked/{userId}")
    public ResponseEntity<List<AnimalGetDto>> getLikedAnimals(@PathVariable(value = "userId") Long userId) {
        User user = userRepository.getReferenceById(userId);
        Set<AnimalGetDto> likedAnimals = user.getLikedAnimals().stream()
                .map(userLike -> mapper.animalToGetDto(userLike.getLikedAnimal()))
                .collect(Collectors.toSet());

        return new ResponseEntity<>(new ArrayList<>(likedAnimals), HttpStatus.OK);
    }
}
