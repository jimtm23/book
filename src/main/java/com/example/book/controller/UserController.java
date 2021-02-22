package com.example.book.controller;

import com.example.book.exception.UserAlreadyExistException;
import com.example.book.helpers.CrudController;
import com.example.book.model.User;
import com.example.book.service.UserRolePermissionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController implements CrudController<User> {

    private final UserRolePermissionService userService;

    @Override
    public ResponseEntity<User> show(String id) {
        User users = userService.getUserById(id);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<Iterable<User>> list() {
        return ResponseEntity.ok(userService.listUser());
    }

    @Override
    public ResponseEntity<?> create(User user) {

        User newUser;
        try {
            newUser = userService.createUser(user);
        } catch (UserAlreadyExistException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return ResponseEntity.created(URI.create("/user/show/" + newUser.getId())).build();
    }

    @Override
    public ResponseEntity<User> edit(String id, User t) {
        User user = userService.editUser(id, t);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Boolean> delete(String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
