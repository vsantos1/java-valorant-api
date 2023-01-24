package com.vsantos1.services;

import com.vsantos1.dtos.AuthDTO;
import com.vsantos1.dtos.RegisterDTO;
import com.vsantos1.dtos.TokenDTO;
import com.vsantos1.enums.Role;
import com.vsantos1.jwt.JwtService;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.User;
import com.vsantos1.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 365L; // 1 YEAR

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public TokenDTO register(RegisterDTO registerDTO, Role role) {

        User user = new User();
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Mapper.copyProperties(registerDTO, user);
        user.setRole(role);
        userService.execute(user);

        return new TokenDTO(accessToken(user), true, issuedAt(), expirationDate());

    }

    public TokenDTO authenticate(AuthDTO authDTO) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getPassword());
        authenticationManager.authenticate(auth);

        User user = userService.findByEmail(authDTO.getEmail());

        return new TokenDTO(accessToken(user), true, issuedAt(), expirationDate());
    }

    public String accessToken(User user) {
        return jwtService.generateToken(user);
    }

    public Date expirationDate() {
        return new Date(issuedAt().getTime() + EXPIRATION_TIME);
    }

    public Date issuedAt() {
        return new Date();
    }

}
