package com.example.book.controller;

import com.example.book.Request.UserRequest;
import com.example.book.exception.UserAlreadyExistException;
import com.example.book.model.User;
import com.example.book.service.UserRolePermissionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserRolePermissionService userService;

    @GetMapping("/show/{id}")
    public ResponseEntity<User> show(String id) {
        User users = userService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/list")
    public ResponseEntity<Iterable<User>> list() {
        return ResponseEntity.ok(userService.listUser());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserRequest user) {
//        ModelMapper modelMapper = new ModelMapper();
        User newUser = new User();/* = modelMapper.map(user, User.class);*/
        newUser.setUsername(user.username());
        newUser.setPassword(user.password());
        newUser.setEmail(user.email());
        newUser.setFirstName(user.firstName());
        newUser.setLastName(user.lastName());
        try {
            newUser = userService.createUser(newUser);
        } catch (UserAlreadyExistException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return ResponseEntity.created(URI.create("/user/show/" + newUser.getId())).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> edit(String id, User user) {
        User thisUser = userService.editUser(id, user);
        return ResponseEntity.ok(thisUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
