package com.example.book.controller;

import com.example.book.model.Permission;
import com.example.book.repository.PermissionRepository;
import com.example.book.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("permission")
public class PermissionController {

    private final PermissionService permissionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/show/{id}")
    public ResponseEntity<Permission> show(@PathVariable String id) {
        Permission permission = permissionService.show(id);
        return ResponseEntity.ok(permission);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<Iterable<Permission>> list() {
        Iterable<Permission> permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Permission permission) {
        Permission perm = permissionService.save(permission);
        return ResponseEntity.created(URI.create("/role/show/" + perm.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<Permission> edit(@PathVariable String id, @RequestBody Permission permission) {
        Permission perm = permissionService.findById(id);
        permissionService.save(permission);
        return ResponseEntity.created(URI.create("/role/show/" + perm.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        permissionService.findById(id);
        return ResponseEntity.ok(true);
    }
}

