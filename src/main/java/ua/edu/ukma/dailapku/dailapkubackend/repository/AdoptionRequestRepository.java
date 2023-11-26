package ua.edu.ukma.dailapku.dailapkubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.dailapku.dailapkubackend.dto.AdoptionRequestUserInfoDto;
import ua.edu.ukma.dailapku.dailapkubackend.model.AdoptionRequest;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Long> {
    @Query("SELECT ar FROM AdoptionRequest ar " +
            "JOIN ar.animal a " +
            "JOIN a.shelter s " +
            "WHERE s.id = :shelterId")
    List<AdoptionRequest> findAllByShelterId(@Param("shelterId") Long shelterId);

    @Query("SELECT ar FROM AdoptionRequest ar WHERE ar.user.id = :userId")
    List<AdoptionRequest> findAllByUserId(@Param("userId") Long userId);

}
