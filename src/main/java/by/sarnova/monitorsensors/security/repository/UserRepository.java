package by.sarnova.monitorsensors.security.repository;

import by.sarnova.monitorsensors.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNameAndPassword(String name, String password);
    Optional<User> findByName(String name);
}
