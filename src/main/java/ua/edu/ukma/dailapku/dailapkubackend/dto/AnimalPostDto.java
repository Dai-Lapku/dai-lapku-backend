package ua.edu.ukma.dailapku.dailapkubackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ua.edu.ukma.dailapku.dailapkubackend.model.Sex;

@Getter
@Setter
public class AnimalPostDto {
    @NotBlank
    @Size(min=2, max=100)
    private String name;
    @NotNull
    private Sex sex;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private java.util.Date birthDate;
    @NotNull
    private SpeciesPostDto species;
    @Size(max=255)
    private String breed;
    @NotNull
    private Long shelterId;
}
