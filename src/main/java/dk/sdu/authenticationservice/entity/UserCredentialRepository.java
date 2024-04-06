package dk.sdu.authenticationservice.entity;

import dk.sdu.authenticationservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    Optional<UserCredential> findByEmail(String email);
}
