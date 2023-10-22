package ua.edu.ukma.dailapku.dailapkubackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShelterPostDto {
    @NotBlank
    @Size(min=5, max=255)
    private String name;
    @NotBlank
    @Size(min=5, max=255)
    private String address;
    @NotBlank
    private String managerUsername;
}
