package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetDto {
    private Long id;
    private String email;
    private String username;
    private String role;
}
