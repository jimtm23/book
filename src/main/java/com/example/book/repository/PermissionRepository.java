package com.example.book.repository;

import com.example.book.model.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission, String> {
    
}
