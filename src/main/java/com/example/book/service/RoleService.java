package com.example.book.service;


import com.example.book.helpers.CrudService;
import com.example.book.model.Role;
import com.example.book.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements CrudService<Role> {

    @Autowired
    RoleRepository roleRepository;


    @Override
    public Role findById(String id) {
        Role thisRole = roleRepository.findById(id).orElse(null);

        return thisRole;
    }

    @Override
    public Role save(Role role) {
        roleRepository.save(role);
        return null;
    }

    @Override
    public Role edit(String id, Role role) {
        Role thisRole = roleRepository.findById(id).orElse(null);

        thisRole.setName(role.getName());

        return roleRepository.save(thisRole);
    }

    @Override
    public Iterable<Role> list() {
        Iterable<Role> roles = roleRepository.findAll();
        roles.forEach(role -> {
            role.getPermissions().forEach(permission -> {
                System.out.println(permission);
            });
        });
        return roles;
    }

    @Override
    public void delete(String id) {
        Role thisRole = roleRepository.findById(id).orElse(null);
        roleRepository.delete(thisRole);
    }
}
