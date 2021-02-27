package com.example.book.controller;

import com.example.book.Request.UserRequest;
import com.example.book.exception.UserAlreadyExistException;
import com.example.book.model.Role;
import com.example.book.model.User;
import com.example.book.service.RoleService;
import com.example.book.service.UserRolePermissionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserRolePermissionService userService;
    private final RoleService roleService;

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
        User newUser = new User();
        List<Role> userRole = new ArrayList<>();
        user.roles().forEach(role -> {
            Role newRole = roleService.findById(role.id());
            userRole.add(newRole);
        });
        newUser.setUsername(user.username());
        newUser.setPassword(user.password());
        newUser.setEmail(user.email());
        newUser.setFirstName(user.firstName());
        newUser.setLastName(user.lastName());
        newUser.setRoles(userRole);
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
    public ResponseEntity<User> edit(@Valid @RequestBody String id,@Valid @RequestBody  User user) {
        User thisUser = userService.editUser(id, user);
        return ResponseEntity.ok(thisUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
