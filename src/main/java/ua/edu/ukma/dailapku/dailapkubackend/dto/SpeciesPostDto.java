package ua.edu.ukma.dailapku.dailapkubackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeciesPostDto {
    @NotBlank
    @Size(min=2, max=255)
    private String name;
}
