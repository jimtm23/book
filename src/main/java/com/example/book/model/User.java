package com.example.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.NotNull;

import java.util.Collection;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    @NotNull
    @Field
    private String username;

    @NotNull
    @Field
    private String firstName;

    @NotNull
    @Field
    private String lastName;

    @NotNull
    @Field
    private String email;

    @NotNull
    @Field
    private String password;

    @NotNull
    @Field
    private Boolean isEnabled = true;

    private Collection<Role> roles;
}
