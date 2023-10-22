package ua.edu.ukma.dailapku.dailapkubackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ua.edu.ukma.dailapku.dailapkubackend.dto.*;
import ua.edu.ukma.dailapku.dailapkubackend.model.Animal;
import ua.edu.ukma.dailapku.dailapkubackend.model.Shelter;
import ua.edu.ukma.dailapku.dailapkubackend.model.Species;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MapStructMapper {
    @Mapping(target = "role", source = "role")
    UserGetDto userToGetDto(User user);

    User postDtoToUser(UserPostDto userPostDto);

    SpeciesGetDto speciesToGetDto(Species species);

    Species postDtoToSpecies(SpeciesPostDto speciesPostDto);

    ShelterGetDto shelterToGetDto(Shelter shelter);

    Shelter postDtoToShelter(ShelterPostDto shelterPostDto);

    AnimalGetDto animalToGetDto(Animal animal);

    Animal postDtoToAnimal(AnimalPostDto animalPostDto);
}
