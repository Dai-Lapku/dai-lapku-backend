package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Getter;
import lombok.Setter;
import ua.edu.ukma.dailapku.dailapkubackend.model.AdoptionStatus;

@Getter
@Setter
public class AdoptionRequestGetDto {
    private Long id;
    private UserGetDto userGetDto;
    private AnimalGetDto animalGetDto;
    private AdoptionStatus status;
}
