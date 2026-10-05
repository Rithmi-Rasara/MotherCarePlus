package com.nibm.mothercare.service;

import com.nibm.mothercare.dto.LoginRequest;
import com.nibm.mothercare.dto.LoginResponse;
import com.nibm.mothercare.dto.UserDto;
import com.nibm.mothercare.entity.User;
import com.nibm.mothercare.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for user authentication.
 * Replaces the business logic in login.php.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {
        if (request.getEmail() == null || request.getEmail().trim().isEmpty() ||
            request.getPassword() == null || request.getPassword().isEmpty()) {
            return new LoginResponse(false, "Email and password are required");
        }

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail().trim());
        if (optionalUser.isEmpty()) {
            return new LoginResponse(false, "Invalid email or password");
        }

        User user = optionalUser.get();
        String storedPassword = user.getPassword();

        boolean passwordValid = false;

        // 1. Try BCrypt verify (password_verify compatibility)
        if (storedPassword != null && storedPassword.startsWith("$2")) {
            passwordValid = passwordEncoder.matches(request.getPassword(), storedPassword);
        }

        // 2. Fallback to plain-text matching (for legacy / demo passwords)
        if (!passwordValid && storedPassword != null && storedPassword.equals(request.getPassword())) {
            passwordValid = true;
        }

        if (!passwordValid) {
            return new LoginResponse(false, "Invalid email or password");
        }

        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getDateOfBirth(),
                user.getRole(),
                user.getCreatedAt()
        );

        return new LoginResponse(true, "Login successful", userDto);
    }
}
