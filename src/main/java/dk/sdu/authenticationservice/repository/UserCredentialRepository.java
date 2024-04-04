package dk.sdu.authenticationservice.repository;

import dk.sdu.authenticationservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
}
