package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Getter;
import lombok.Setter;
import ua.edu.ukma.dailapku.dailapkubackend.model.AdoptionStatus;

@Getter
@Setter
public class AdoptionRequestPostDto {
    private Long id;
    private Long userId;
    private Long animalId;
    private AdoptionStatus status;
}