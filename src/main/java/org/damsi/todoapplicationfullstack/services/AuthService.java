package org.damsi.todoapplicationfullstack.services;

import org.damsi.todoapplicationfullstack.dtos.AuthRequest;
import org.damsi.todoapplicationfullstack.dtos.AuthResponse;
import org.damsi.todoapplicationfullstack.models.User;
import org.damsi.todoapplicationfullstack.repositories.UserRepository;
import org.damsi.todoapplicationfullstack.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register (AuthRequest request){
        // Check if username exists
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken.");
        }

        // Check if email exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use.");
        }

        // Encrypt password
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getFullName(), request.getUsername(), request.getEmail(),
                encodedPassword);

        user = userRepository.save(user);

        return new AuthResponse(jwtUtil.generateToken(user.getUsername()));
    }

    public AuthResponse login (AuthRequest request){
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isPresent() && passwordEncoder.matches(request.getPassword(), user.get().getPassword())){
            return new AuthResponse(jwtUtil.generateToken(user.get().getUsername()));
        }
        throw new RuntimeException("Invalid username or password.");
    }
}
