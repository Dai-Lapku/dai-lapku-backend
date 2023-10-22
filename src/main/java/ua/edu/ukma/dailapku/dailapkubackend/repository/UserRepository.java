package ua.edu.ukma.dailapku.dailapkubackend.repository;

import ua.edu.ukma.dailapku.dailapkubackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
