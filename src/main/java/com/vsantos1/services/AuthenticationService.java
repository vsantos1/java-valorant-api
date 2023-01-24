package com.vsantos1.services;

import com.vsantos1.dtos.RegisterDTO;
import com.vsantos1.enums.Role;
import com.vsantos1.jwt.JwtService;
import com.vsantos1.models.User;
import com.vsantos1.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String register(RegisterDTO registerDTO) {
        User user = new User();

        user.setEmail(registerDTO.getEmail());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return jwtService.generateToken(user);

    }
}
