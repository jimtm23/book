package com.example.book.service;

import com.example.book.model.Permission;
import com.example.book.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    public Permission show(String id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public Iterable<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public Permission save(Permission permission) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.."+permission.getName());
        return permissionRepository.save(permission);
    }

    public Permission findById(String id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public void delete(String id) {
        Permission perm = permissionRepository.findById(id).orElse(null);
        permissionRepository.delete(perm);
    }
}
