package com.example.book.service;

import com.example.book.exception.UserAlreadyExistException;
import com.example.book.model.Permission;
import com.example.book.model.Role;
import com.example.book.model.User;
import com.example.book.repository.PermissionRepository;
import com.example.book.repository.RoleRepository;
import com.example.book.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRolePermissionService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

	public User createUser(User user) throws UserAlreadyExistException {

        if (checkIfEmailExist(user.getEmail())) {
            throw new UserAlreadyExistException("Email Already Exist!");
        }

        if (checkIfUsernameExist(user.getUsername())) {
            throw new UserAlreadyExistException("Username Already Exist!");
        }

	    user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	public Iterable<User> listUser() {
	    return userRepository.findAll();
    }
    public User getUserById(String id) {
	    return userRepository.findById(id).orElse(null);
    }
    public Boolean deleteUser(String id) {
	    User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);
	    return true;
    }

    public User editUser(String id, User userDetail) {
	    User user = userRepository.findById(id).orElse(null);
	    user.setFirstName(userDetail.getFirstName());
	    user.setLastName(userDetail.getLastName());
	    user.setUsername(userDetail.getUsername());
	    user.setPassword(userDetail.getPassword());
	    return userRepository.save(user);
    }

    public boolean checkIfEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean checkIfUsernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

}
