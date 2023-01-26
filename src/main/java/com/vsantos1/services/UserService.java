package com.vsantos1.services;

import com.vsantos1.dtos.UserDTO;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.User;
import com.vsantos1.repositories.UserRepository;
import com.vsantos1.repositories.filter.UserQueryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(User user) {

        userRepository.save(user);
    }


    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("User or password invalid, try again.");
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }
        throw new UsernameNotFoundException("No records found for this ID");
    }

    public User update(Long id, UserDTO userDTO) {
        User user = this.findById(id);

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }


        Mapper.copyProperties(userDTO, user);

        return userRepository.save(user);
    }

    public void delete(Long id) {
        User entity = this.findById(id);
        userRepository.deleteById(entity.getId());
    }

    public Page<User> findAllOrQueryPaginated(Pageable pageable, UserQueryFilter query) {
        if (query.getFirstname() == null && query.getLastname() == null) {
            return userRepository.findAll(pageable);
        }
        return userRepository.findUserByFirstNameOrLastNameContainingIgnoreCase(query.getFirstname(), query.getLastname(), pageable);
    }

    public void activate(Long id) {
        User user = this.findById(id);
        user.setEnable(true);
        userRepository.save(user);
    }

    public void inactivate(Long id) {
        User user = this.findById(id);
        user.setEnable(false);
        userRepository.save(user);
    }
}
