package ua.edu.ukma.dailapku.dailapkubackend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdoptionRequestUserInfoDto {
    private Long id;
    private String userEmail;
    private UserProfileGetDto userProfile;
}
