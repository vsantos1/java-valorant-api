package com.vsantos1.resources;

import com.vsantos1.dtos.AuthDTO;
import com.vsantos1.dtos.UserDTO;
import com.vsantos1.dtos.TokenDTO;
import com.vsantos1.enums.Role;
import com.vsantos1.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    public AuthenticationResource(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;

    }

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody @Valid UserDTO userDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(userDTO, Role.ROLE_USER));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authenticationService.authenticate(authDTO));
    }
}
