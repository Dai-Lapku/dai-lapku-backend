package ua.edu.ukma.dailapku.dailapkubackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
    @NotNull
    @Email
    @Size(min=3, max=255)
    private String email;

    @NotBlank
    @Size(min=3, max=255)
    private String username;

    @NotBlank
    @Size(min=8,max=255)
    private String password;
}
