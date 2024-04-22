package dk.sdu.authenticationservice.repository;

import dk.sdu.authenticationservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
