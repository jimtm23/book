package com.example.book.controller;

import com.example.book.helpers.CrudController;
import com.example.book.model.Role;
import com.example.book.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {

    RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/show/{id}")
    public ResponseEntity<Role> show(String id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

//    @PreAuthorize("hasAnyRole('IS_AUTHENTICATED_ANONYMOUSLY')")
    @GetMapping("/list")
    public ResponseEntity<Iterable<Role>> list() {
        return ResponseEntity.ok(roleService.list());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Role> create(Role role) {
        Role newRole = roleService.save(role);
        return ResponseEntity.created(URI.create("/role/show/" + newRole.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Role> edit(String id, Role role) {
        Role thisRole = roleService.edit(id, role);
        return ResponseEntity.created(URI.create("/role/show/" + thisRole.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(String id) {
        roleService.delete(id);
        return ResponseEntity.ok(true);
    }
}
