package ua.edu.ukma.dailapku.dailapkubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.edu.ukma.dailapku.dailapkubackend.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
