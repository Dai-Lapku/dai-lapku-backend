package ua.edu.ukma.dailapku.dailapkubackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.dailapku.dailapkubackend.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
