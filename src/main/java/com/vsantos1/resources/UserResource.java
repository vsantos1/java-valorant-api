package com.vsantos1.resources;

import com.vsantos1.dtos.UserDTO;
import com.vsantos1.jwt.JwtService;
import com.vsantos1.models.User;
import com.vsantos1.repositories.filter.UserQueryFilter;
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

    private final JwtService jwtService;

    public UserResource(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/users/token")
    public ResponseEntity<User> getByToken(@RequestHeader String Authorization) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.loadUserByToken(Authorization));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<User>> getAll(@PageableDefault(size = 10, direction = Sort.Direction.ASC, page = 0, value = 10) Pageable pageable,
                                             UserQueryFilter queryFilter) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllOrQueryPaginated(pageable, queryFilter));
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<User> update(@PathVariable("user_id") Long id, @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, userDTO));
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

