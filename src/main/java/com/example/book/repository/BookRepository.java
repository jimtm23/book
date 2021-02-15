package com.example.book.repository;

import com.example.book.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository  extends CrudRepository<Book, String> {
    List<Book> findAllByTitle(String title);
    List<Book> findAllByTitleLike(String title);
}
