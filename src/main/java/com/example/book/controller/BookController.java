package com.example.book.controller;

import com.example.book.Request.BookRequest;
import com.example.book.Request.SampleRequest;
import com.example.book.model.Book;
import com.example.book.service.BookService;
import com.example.book.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("book")
@RequiredArgsConstructor // para maging private final yung service
public class BookController {
    // push sa develop branch
    private final BookService bookService;
    private final FileStorageService fileStorageService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/list")
    public ResponseEntity<Iterable<Book>> list() {
        Iterable<Book> books = bookService.findAll();

        return ResponseEntity.ok(books);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Book> save(@Valid @RequestBody BookRequest book) {

        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Book show(@PathVariable String id) {
        return bookService.findById(id);
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Book edit(@PathVariable String id, @RequestBody BookRequest book) {
        return bookService.edit(id, book);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/uploadImage", consumes = {"multipart/form-data"})
    public String uploadImage(@ModelAttribute SampleRequest sr) {
        fileStorageService.uploadFile(sr.getImage());
        String message =  "You successfully uploaded " + sr.getImage().getOriginalFilename() + "!";
        System.out.println(message);
        return message;
    }

    @GetMapping("search/{title}")
    public ResponseEntity<Iterable<Book>> findAllByTitle(@PathVariable String title) {
        Iterable<Book> books = bookService.findAllByTitle(title);
        return ResponseEntity.ok(books);
    }
}
