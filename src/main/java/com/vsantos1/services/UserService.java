package com.vsantos1.services;

import com.vsantos1.dtos.RegisterDTO;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.User;
import com.vsantos1.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User update(Long id, RegisterDTO registerDTO) {
        User user = this.findById(id);


        Mapper.copyProperties(registerDTO, user);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User entity = this.findById(id);
        userRepository.deleteById(entity.getId());
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
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
