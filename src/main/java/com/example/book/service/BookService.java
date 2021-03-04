package com.example.book.service;

import com.example.book.Request.BookRequest;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.model.Author;
import com.example.book.model.Book;
import com.example.book.repository.AuthorRepository;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    FileStorageService fileStorageService;

    @Transactional
    public Book save(BookRequest bookRequest) {
        Book newBook = new Book();
        List<Author> authors = new ArrayList<>();
        bookRequest.authors().forEach(author -> {
            Author a = new Author();
            a.setFullName(author.fullName());
            authors.add(a);
        });
        authorRepository.saveAll(authors);
        newBook.setISBN(bookRequest.ISBN());
//        newBook.setAuthors(authors);
        newBook.setTitle(bookRequest.title());
        newBook.setDescription(bookRequest.description());
        newBook.setImage(bookRequest.image());
        newBook.setPublisher(bookRequest.publisher());
        newBook.setDateAdded( bookRequest.date()); //
        return bookRepository.save(newBook);
    }

    @Transactional
    public Book edit(String id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElse(null);
        List<Author> authors = new ArrayList<>();
        bookRequest.authors().forEach(author -> {
            Author a = new Author();
            a.setFullName(author.fullName());
            authors.add(a);
        });
//        authorRepository.saveAll(authors);
        book.setISBN(bookRequest.ISBN());
        book.setAuthors(authors);
        book.setTitle(bookRequest.title());
        book.setDescription(bookRequest.description());
        book.setPublisher(bookRequest.publisher());
        book.setImage(bookRequest.image());
        book.setDateAdded(bookRequest.date());
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

    public Boolean saveAll(List<BookRequest> books) {
        List<Book> newBooks = new ArrayList<>();
        books.forEach(bookRequest -> {
            Book newBook = new Book();
            List<Author> authors = new ArrayList<>();
            bookRequest.authors().forEach(author -> {
                Author a = new Author();
                a.setFullName(author.fullName());
                authors.add(a);
            });
//            authorRepository.saveAll(authors);
            newBook.setISBN(bookRequest.ISBN());
            newBook.setAuthors(authors);
            newBook.setTitle(bookRequest.title());
            newBook.setDescription(bookRequest.description());
            newBook.setImage(bookRequest.image());
            newBook.setPublisher(bookRequest.publisher());
            newBook.setDateAdded( bookRequest.date());
            newBooks.add(newBook);
        } );
        bookRepository.saveAll(newBooks);
        return true;
    }
}
