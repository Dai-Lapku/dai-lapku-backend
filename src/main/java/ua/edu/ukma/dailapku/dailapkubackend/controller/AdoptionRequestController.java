package ua.edu.ukma.dailapku.dailapkubackend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AdoptionRequestGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AdoptionRequestPostDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AdoptionRequestUserInfoDto;
import ua.edu.ukma.dailapku.dailapkubackend.dto.UserProfileGetDto;
import ua.edu.ukma.dailapku.dailapkubackend.mapper.MapStructMapper;
import ua.edu.ukma.dailapku.dailapkubackend.model.AdoptionRequest;
import ua.edu.ukma.dailapku.dailapkubackend.model.AdoptionStatus;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;
import ua.edu.ukma.dailapku.dailapkubackend.repository.AdoptionRequestRepository;

import java.util.List;

@RestController
@RequestMapping("/adoption-requests")
public class AdoptionRequestController {

    @Autowired
    private MapStructMapper mapper;

    @Autowired
    private AdoptionRequestRepository adoptionRequestRepository;

    @PostMapping()
    public ResponseEntity<Void> submitAdoptionRequest(@Valid @RequestBody AdoptionRequestPostDto adoptionRequestPostDto) {
        adoptionRequestPostDto.setStatus(AdoptionStatus.PENDING);
        adoptionRequestRepository.save(mapper.adoptionRequestPostDtoToEntity(adoptionRequestPostDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdoptionRequestGetDto>> getUserAdoptionRequests(@PathVariable Long userId) {
        List<AdoptionRequest> userAdoptionRequests = adoptionRequestRepository.findAllByUserId(userId);
        List<AdoptionRequestGetDto> adoptionRequestDtos = mapper.adoptionRequestsToGetDtoList(userAdoptionRequests);
        return new ResponseEntity<>(adoptionRequestDtos, HttpStatus.OK);
    }

    @DeleteMapping("/user/{requestId}")
    public ResponseEntity<Void> deleteUserAdoptionRequest(@PathVariable Long requestId) {
        adoptionRequestRepository.deleteById(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdoptionRequestGetDto>> getAdoptionRequests() {
        return new ResponseEntity<>(
                mapper.adoptionRequestsToGetDtoList(adoptionRequestRepository.findAll()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdoptionRequestGetDto> getAdoptionRequestById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.adoptionRequestsToGetDto(adoptionRequestRepository.findById(id).get()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/shelter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdoptionRequestGetDto>> getAdoptionRequestsByShelterId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.adoptionRequestsToGetDtoList(adoptionRequestRepository.findAllByShelterId(id)),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdoptionRequestGetDto>> getAdoptionRequestsByUserId(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                mapper.adoptionRequestsToGetDtoList(adoptionRequestRepository.findAllByUserId(id)),
                HttpStatus.OK
        );
    }

    @PutMapping("/accept/{requestId}")
    public ResponseEntity<Void> acceptAdoptionRequest(@PathVariable Long requestId) {
        return updateAdoptionRequestStatus(requestId, AdoptionStatus.ACCEPTED);
    }

    @PutMapping("/reject/{requestId}")
    public ResponseEntity<Void> rejectAdoptionRequest(@PathVariable Long requestId) {
        return updateAdoptionRequestStatus(requestId, AdoptionStatus.REJECTED);
    }

    private ResponseEntity<Void> updateAdoptionRequestStatus(Long requestId, AdoptionStatus status) {
        var adoptionRequest = adoptionRequestRepository.getReferenceById(requestId);
        adoptionRequest.setStatus(status);
        adoptionRequestRepository.save(adoptionRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/info/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdoptionRequestUserInfoDto> getAdoptionRequestWithUserProfile(@PathVariable(value = "id") Long requestId) {
        AdoptionRequest adoptionRequest = adoptionRequestRepository.getReferenceById(requestId);

        User user = adoptionRequest.getUser();
        UserProfileGetDto userProfileDto = mapper.userProfileToGetDto(user.getUserProfile());

        AdoptionRequestUserInfoDto resultDto = AdoptionRequestUserInfoDto.builder()
                .id(adoptionRequest.getId())
                .userEmail(user.getEmail())
                .userProfile(userProfileDto)
                .build();

        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

}

