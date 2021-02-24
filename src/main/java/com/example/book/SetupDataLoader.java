package com.example.book;

import com.example.book.model.Permission;
import com.example.book.model.Role;
import com.example.book.model.User;
import com.example.book.repository.PermissionRepository;
import com.example.book.repository.RoleRepository;
import com.example.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /*if (alreadySetup)
            return;
        Permission readPermission
                = createPermissionIfNotFound("READ_PRIVILEGE");
        Permission writePermission
                = createPermissionIfNotFound("WRITE_PRIVILEGE");

        List<Permission> adminPrivileges = Arrays.asList(readPermission, writePermission);
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        Role userRole = createRoleIfNotFound("ROLE_USER", Arrays.asList(readPermission));

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin1"));
        admin.setEmail("admin@admin.com");
        admin.setRoles(Arrays.asList(adminRole));
        userRepository.save(admin);

        User user = new User();
        user.setFirstName("User");
        user.setLastName("User");
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user1"));
        user.setEmail("user@user.com");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        alreadySetup = true;*/
    }

    @Transactional
    Permission createPermissionIfNotFound(String name) {

        Permission privilege = permissionRepository.findByName(name);
        if (privilege == null) {
            privilege = new Permission(name);
            permissionRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, List<Permission> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPermissions(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}