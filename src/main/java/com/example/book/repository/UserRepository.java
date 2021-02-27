package com.example.book.repository;


import com.example.book.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String userName);

    User findByEmail(String email);
}