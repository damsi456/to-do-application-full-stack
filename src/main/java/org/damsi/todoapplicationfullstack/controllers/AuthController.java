package org.damsi.todoapplicationfullstack.controllers;

import org.damsi.todoapplicationfullstack.dtos.AuthRequest;
import org.damsi.todoapplicationfullstack.dtos.AuthResponse;
import org.damsi.todoapplicationfullstack.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request){
        return authService.login(request);
    }
}
