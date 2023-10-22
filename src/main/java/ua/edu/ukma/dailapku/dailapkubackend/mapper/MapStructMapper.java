package ua.edu.ukma.dailapku.dailapkubackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import ua.edu.ukma.dailapku.dailapkubackend.dto.*;
import ua.edu.ukma.dailapku.dailapkubackend.model.Animal;
import ua.edu.ukma.dailapku.dailapkubackend.model.Shelter;
import ua.edu.ukma.dailapku.dailapkubackend.model.Species;
import ua.edu.ukma.dailapku.dailapkubackend.model.User;

import java.util.List;

// TODO: fix unmapped values
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MapStructMapper {
    // FIXME: next line do not compile sometimes, just comment it, build, then uncomment and build again
    @Mapping(target = "role", source = "role")
    UserGetDto userToGetDto(User user);

    List<UserGetDto> usersToDtoList(List<User> users);

    User postDtoToUser(UserPostDto userPostDto);

    void updateUserFromDto(UserPostDto dto, @MappingTarget User user);

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
}
