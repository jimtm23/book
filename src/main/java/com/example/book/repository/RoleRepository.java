package com.example.book.repository;

import com.example.book.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {
    Role findByName(String role_admin);
}
