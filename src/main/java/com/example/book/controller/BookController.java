package com.example.book.controller;

import com.example.book.Request.BookRequest;
import com.example.book.model.Book;
import com.example.book.service.BookService;
import com.example.book.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("book")
@RequiredArgsConstructor // para maging private final yung service
public class BookController {

    private final BookService bookService;
    private final FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<Iterable<Book>> bookList() {
        Iterable<Book> books = bookService.findAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Book> save(@Valid @RequestBody BookRequest book) {

        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping("/show/{id}")
    public Book show(@PathVariable String id) {
        return bookService.findById(id);
    }

    @PutMapping("/edit/{id}")
    public Book edit(@PathVariable String id, @RequestBody BookRequest book) {
        return bookService.edit(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/uploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile image)  {
        fileStorageService.uploadFile(image);
        String message =  "You successfully uploaded " + image.getOriginalFilename() + "!";
        return message;
    }

    @GetMapping("search/title/{title}")
    public ResponseEntity<Iterable<Book>> findAllByTitle(@PathVariable String title) {
        Iterable<Book> books = bookService.findAllByTitle(title);
        return ResponseEntity.ok(books);
    }
}