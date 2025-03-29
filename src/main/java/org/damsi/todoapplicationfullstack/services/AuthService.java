package org.damsi.todoapplicationfullstack.services;

import org.damsi.todoapplicationfullstack.dtos.AuthRequest;
import org.damsi.todoapplicationfullstack.dtos.AuthResponse;
import org.damsi.todoapplicationfullstack.models.User;
import org.damsi.todoapplicationfullstack.repositories.UserRepository;
import org.damsi.todoapplicationfullstack.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthResponse register (AuthRequest request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new RuntimeException("Username is already taken.");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email is already in use.");
        }

        User user = new User(request.getFullName(), request.getUsername(), request.getEmail(),
                passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
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
