package com.vsantos1.services;

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

    public User update(User user, Long id) {
        User entity = this.findById(id);

        // TODO : add model mapper and map the given properties
        BeanUtils.copyProperties(user, entity, "id");

        return userRepository.save(entity);
    }

    public void delete(Long id) {
        User entity = this.findById(id);
        userRepository.deleteById(entity.getId());
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
