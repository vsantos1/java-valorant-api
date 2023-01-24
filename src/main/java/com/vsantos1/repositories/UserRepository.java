package com.vsantos1.repositories;

import com.vsantos1.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Page<User> findUserByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
