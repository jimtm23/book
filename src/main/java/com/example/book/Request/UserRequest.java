package com.example.book.Request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.example.book.model.Role;

import java.util.Collection;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Please enter a username!")
    @NotNull(message = "Cannot be null")
    private String username;
    
    @NotBlank(message = "Please enter a firstname!")
    @NotNull(message = "Cannot be null")
    private String firstName;
    
    @NotBlank(message = "Please enter a lastname!")
    @NotNull(message = "Cannot be null")
    private String lastName;
    
    @NotBlank(message = "Please enter an email!")
    @NotNull(message = "Cannot be null")
    private String email;
    
    @NotBlank(message = "Please enter a password!")
    @NotNull(message = "Cannot be null")
    private String password;
    
    private Collection<RoleRequest> roles;
}
