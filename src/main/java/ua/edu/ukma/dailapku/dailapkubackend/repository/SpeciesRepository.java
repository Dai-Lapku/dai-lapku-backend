package ua.edu.ukma.dailapku.dailapkubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.dailapku.dailapkubackend.model.Species;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
