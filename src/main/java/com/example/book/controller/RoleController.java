package com.example.book.controller;

import com.example.book.helpers.CrudController;
import com.example.book.model.Role;
import com.example.book.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController implements CrudController<Role> {

    RoleService roleService;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Role> show(String id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Iterable<Role>> list() {
        return ResponseEntity.ok(roleService.list());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Role> create(Role role) {
        Role newRole = roleService.save(role);
        return ResponseEntity.created(URI.create("/role/show/" + newRole.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Role> edit(String id, Role role) {
        Role thisRole = roleService.edit(id, role);
        return ResponseEntity.created(URI.create("/role/show/" + thisRole.getId())).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<Boolean> delete(String id) {
        roleService.delete(id);
        return ResponseEntity.ok(true);
    }
}
