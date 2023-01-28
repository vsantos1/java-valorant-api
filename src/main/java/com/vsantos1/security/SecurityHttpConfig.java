package com.vsantos1.security;

import com.vsantos1.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider;

@EnableWebSecurity
@Configuration
public class SecurityHttpConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    public SecurityHttpConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/users/activate/**").permitAll()
                .requestMatchers("/api/v1/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/games").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/games/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/maps").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/maps/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/agents").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/agents/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/pixels").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/pixels/**").permitAll()
                .requestMatchers("/api/v1/pixels/edit/**").hasRole("ADMIN")
                .requestMatchers("/api/v1/pixels/delete/**").hasRole("ADMIN")
                .requestMatchers("/error").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
