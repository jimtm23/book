package com.example.book.repository;

import com.example.book.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, String> {
    Author findByFullName(String fullName);
}
