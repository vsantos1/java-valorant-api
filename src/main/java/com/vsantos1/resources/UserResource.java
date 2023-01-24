package com.vsantos1.resources;

import com.vsantos1.dtos.RegisterDTO;
import com.vsantos1.models.User;
import com.vsantos1.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAll(@PageableDefault(size = 10, direction = Sort.Direction.ASC, page = 0, value = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<User> update(@PathVariable("user_id") Long id, @RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, registerDTO));
    }

    @PatchMapping("/users/activate/{user_id}")
    public ResponseEntity<Void> activate(@PathVariable("user_id") Long id) {
        userService.activate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/users/inactivate/{user_id}")
    public ResponseEntity<Void> inactivate(@PathVariable("user_id") Long id) {
        userService.inactivate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Void> delete(@PathVariable("user_id") Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

