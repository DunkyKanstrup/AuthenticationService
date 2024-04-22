package dk.sdu.authenticationservice.service;

import dk.sdu.authenticationservice.entity.User;
import dk.sdu.authenticationservice.model.AuthRequest;
import dk.sdu.authenticationservice.model.AuthResponse;
import dk.sdu.authenticationservice.model.LoginRequest;
import dk.sdu.authenticationservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository repository;
    @Autowired
    private JwtService jwtService;

    public AuthResponse register(AuthRequest authRequest) {
        User user = User.builder()
                .name(authRequest.getName())
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .build();

        try {
            repository.save(user);

            String accessToken = jwtService.generate(authRequest.getEmail(), "ACCESS");
            String refreshToken = jwtService.generate(authRequest.getEmail(), "REFRESH");

            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return AuthResponse.builder()
                    .errorMessage("Registration failed")
                    .build();
        }
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = repository.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String accessToken = jwtService.generate(loginRequest.getEmail(), "ACCESS");
            String refreshToken = jwtService.generate(loginRequest.getEmail(), "REFRESH");

            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            return AuthResponse.builder()
                    .errorMessage("Invalid email or password")
                    .build();
        }
    }
}
