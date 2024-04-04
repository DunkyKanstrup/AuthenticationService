package dk.sdu.authenticationservice.service;

import dk.sdu.authenticationservice.entity.UserCredential;
import dk.sdu.authenticationservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        repository.save(credential);

        return "User added to the PARTY";
    }
}