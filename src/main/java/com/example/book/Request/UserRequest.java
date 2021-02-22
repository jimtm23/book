package com.example.book.Request;

import com.example.book.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<Role> roles;
}
