package com.teruel.feramakers.service;

import com.teruel.feramakers.dto.AuthenticationResponse;
import com.teruel.feramakers.dto.LoginRequest;
import com.teruel.feramakers.dto.RegisterRequest;
import com.teruel.feramakers.exceptions.FeraMakersException;
import com.teruel.feramakers.model.User;
import com.teruel.feramakers.model.VerificationToken;
import com.teruel.feramakers.repository.UserRepository;
import com.teruel.feramakers.repository.VerificationTokenRepository;
import com.teruel.feramakers.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final JwtProvider jwtProvider;

    //private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository,
                       VerificationTokenRepository verificationTokenRepository,
                       JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }


    public void signup (RegisterRequest registerRequest){

        User user = new User();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedTime(Instant.now());
        user.setEnabled(true);

        userRepository.save(user);

//      Not sending email for activation now. User beign activated directed.
//        String token = generateVerificationToken(user);
//        mailService.sendMail(new NotificationEmail("Please Activate your Account",
//                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
//                "please click on the below url to activate your account : " +
//                "http://localhost:8080/api/auth/accountVerification/" + token));


    }

    public String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setCreatedDate(Instant.now());

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {

       Optional<VerificationToken> verificationToken =  verificationTokenRepository.findByToken(token);
       verificationToken.orElseThrow(() -> new FeraMakersException("Token Invalida"));
       fetchUserAndEnable(verificationToken.get());
    }


    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        userRepository.findByUsername(username).orElseThrow(() -> new FeraMakersException("Usuário não encontrado!"));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

      Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername() ,
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());

    }
}
