package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Getter;
import lombok.Setter;
import ua.edu.ukma.dailapku.dailapkubackend.model.Sex;

@Getter
@Setter
public class AnimalGetDto {
    private Long id;
    private String name;
    private Sex sex;
    private java.util.Date birthDate;
    private SpeciesGetDto species;
    private String breed;
    private ShelterGetDto shelterId;
}
