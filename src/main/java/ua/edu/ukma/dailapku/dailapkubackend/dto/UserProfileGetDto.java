package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileGetDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String details;
}

