package com.example.springSecurity2.test3.Auth;//package com.example.springSecurity2.Test1.Auth;

import com.example.springSecurity2.test3.Config.JwtService;
import com.example.springSecurity2.test3.User.AppUser;
import com.example.springSecurity2.test3.User.Role;
import com.example.springSecurity2.test3.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

//
//        var appUser = new AppUser();
//        appUser.setFullName(request.getFirstName());
//        appUser.setLastName(request.getLastName());
//        appUser.setEmail(request.getEmail());
//        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
//        appUser.setRole(Role.USER);
//
//        userRepository.save(appUser);

        var AppUser = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(AppUser);
        


        var jwttoken = jwtService.generateToken(AppUser);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var appUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
