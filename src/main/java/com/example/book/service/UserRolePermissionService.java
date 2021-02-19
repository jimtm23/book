package com.example.book.service;

import com.example.book.model.Permission;
import com.example.book.model.Role;
import com.example.book.model.User;
import com.example.book.repository.PermissionRepository;
import com.example.book.repository.RoleRepository;
import com.example.book.repository.UserEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRolePermissionService {
    
    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

	public User createUser(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userEntityRepository.save(user);
	}
	public Iterable<User> listUser() {
	    return userEntityRepository.findAll();
    }
    public User getUserById(String id) {
	    return userEntityRepository.findById(id).orElse(null);
    }
    public Boolean deleteUser(String id) {
	    User user = userEntityRepository.findById(id).orElse(null);
        userEntityRepository.delete(user);
	    return true;
    }

    public User editUser(String id, User userDetail) {
	    User user = userEntityRepository.findById(id).orElse(null);
	    user.setFirstName(userDetail.getFirstName());
	    user.setLastName(userDetail.getLastName());
	    user.setUsername(userDetail.getUsername());
	    user.setPassword(userDetail.getPassword());
	    return userEntityRepository.save(user);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

}
