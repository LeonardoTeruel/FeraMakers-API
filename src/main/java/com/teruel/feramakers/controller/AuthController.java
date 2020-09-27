package com.teruel.feramakers.controller;

import com.teruel.feramakers.dto.AuthenticationResponse;
import com.teruel.feramakers.dto.LoginRequest;
import com.teruel.feramakers.dto.RegisterRequest;
import com.teruel.feramakers.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup (@RequestBody RegisterRequest registerRequest) {

        authService.signup(registerRequest);
        return new ResponseEntity<>("Usuário Registrado com Sucesso!",
                HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount (@PathVariable String token) {

        authService.verifyAccount(token);
        return new ResponseEntity<>("Conta ativada com sucesso!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login (@RequestBody LoginRequest loginRequest) {

       return authService.login(loginRequest);

    }
}
