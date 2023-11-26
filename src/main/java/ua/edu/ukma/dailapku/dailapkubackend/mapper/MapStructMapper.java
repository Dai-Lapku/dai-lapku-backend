package ua.edu.ukma.dailapku.dailapkubackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ua.edu.ukma.dailapku.dailapkubackend.dto.*;
import ua.edu.ukma.dailapku.dailapkubackend.model.*;

import java.util.List;

// TODO: fix unmapped values
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MapStructMapper {
    // FIXME: next line do not compile sometimes, just comment it, build, then uncomment and build again
    UserGetDto userToGetDto(User user);

    List<UserGetDto> usersToDtoList(List<User> users);
    User postDtoToUser(UserPostDto userPostDto);

    void updateUserFromDto(UserPostDto dto, @MappingTarget User user);

    UserProfile userProfilePostDtoToEntity(UserProfilePostDto userProfilePostDto);

    UserProfileGetDto userProfileToGetDto(UserProfile userProfile);

    List<UserProfileGetDto> userProfilesToGetDtoList(List<UserProfile> userProfiles);

    void updateUserProfileFromDto(UserProfilePostDto dto, @MappingTarget UserProfile userProfile);

    AdoptionRequestGetDto adoptionRequestsToGetDto(AdoptionRequest adoptionRequest);
    List<AdoptionRequestGetDto> adoptionRequestsToGetDtoList(List<AdoptionRequest> adoptionRequests);

    AdoptionRequest adoptionRequestPostDtoToEntity(AdoptionRequestPostDto adoptionRequestPostDto);

    SpeciesGetDto speciesToGetDto(Species species);

    List<SpeciesGetDto> speciesToDtoList(List<Species> species);

    Species postDtoToSpecies(SpeciesPostDto speciesPostDto);

    void updateSpeciesFromDto(SpeciesPostDto dto, @MappingTarget Species species);

    ShelterGetDto shelterToGetDto(Shelter shelter);

    List<ShelterGetDto> sheltersToDtoList(List<Shelter> shelters);

    Shelter postDtoToShelter(ShelterPostDto shelterPostDto);

    void updateShelterFromDto(ShelterPostDto dto, @MappingTarget Shelter shelter);

    AnimalGetDto animalToGetDto(Animal animal);

    List<AnimalGetDto> animalsToDtoList(List<Animal> animals);

    Animal postDtoToAnimal(AnimalPostDto animalPostDto);

    void updateShelterFromDto(AnimalPostDto dto, @MappingTarget Animal animal);

    // Add a default method for mapping enums
    default String map(Role gender) {
        return gender.name();
    }

    // Add a default method for reverse mapping enums
    default Role map(String gender) {
        return Role.valueOf(gender);
    }
}
