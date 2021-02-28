package com.example.book.service;

import com.example.book.Request.BookRequest;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.model.Book;
import com.example.book.model.FileStorage;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    FileStorageService fileStorageService;

    @Transactional
    public Book save(BookRequest bookRequest) {
        Book newBook = new Book();
        newBook.setAuthor(bookRequest.author());
        newBook.setTitle(bookRequest.title());
        newBook.setDescription(bookRequest.description());
        newBook.setImage(bookRequest.image());
        newBook.setPublisher(bookRequest.publisher());
        newBook.setDate(bookRequest.date());
        return bookRepository.save(newBook);
    }

    @Transactional
    public Book edit(String id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setAuthor(bookRequest.author());
        book.setTitle(bookRequest.title());
        book.setDescription(bookRequest.description());
        book.setPublisher(bookRequest.publisher());
        book.setImage(bookRequest.image());
        book.setDate(bookRequest.date());
        bookRepository.save(book);
        return book;
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(String id) {
        Book thisBook = null;
        try {
            thisBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found!"));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return thisBook;
    }

    public void delete(String id) {
        Book book = bookRepository.findById(id).orElse(null);
        bookRepository.delete(book);
    }

    public List<Book> findAllByTitle(String title) {
        List<Book> books = bookRepository.findAllByTitleLike("%"+title+"%");
        return books;
    }
}
