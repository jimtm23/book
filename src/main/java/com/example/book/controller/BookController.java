package com.example.book.controller;

import com.couchbase.client.java.search.result.SearchResult;
import com.example.book.Request.BookRequest;
import com.example.book.Request.BookResponse;
import com.example.book.Request.SampleRequest;
import com.example.book.model.Book;
import com.example.book.model.FileStorage;
import com.example.book.service.BookService;
import com.example.book.service.FileStorageService;
import com.example.book.service.FullTextSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("book")
@RequiredArgsConstructor // para maging private final yung service
public class BookController {
    // push sa develop branch
    private final BookService bookService;
    private final FileStorageService fileStorageService;
    private final FullTextSearchService fts;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/list")
    public ResponseEntity<Iterable<BookResponse>> list() {
        Iterable<Book> books = bookService.findAll();
        Iterable<BookResponse> brs = new ArrayList<>();

        books.forEach(book -> {
            BookResponse br = new BookResponse();
            br.id(book.getId());
            br.authors(book.getAuthors());
            br.description(book.getDescription());
            br.image(book.getImage());
            br.title(book.getTitle());
            br.publisher(book.getPublisher());
            br.date(book.getDateAdded());
            br.ISBN(book.getISBN());
            ((ArrayList<BookResponse>) brs).add(br);
        });
        return ResponseEntity.ok(brs);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Book> save(@Valid @RequestBody BookRequest book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @GetMapping("/show/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public BookResponse show(@PathVariable String id) {
        Book book = bookService.findById(id);
        BookResponse br = new BookResponse();
        br.id(book.getId());
        br.authors(book.getAuthors());
        br.description(book.getDescription());
        br.image(book.getImage());
        br.title(book.getTitle());
        br.publisher(book.getPublisher());
        br.ISBN(book.getISBN());
        br.date(book.getDateAdded());
        return br;
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

    @GetMapping("search/{title}")
    public ResponseEntity<Iterable<Book>> findAllByTitle(@PathVariable String title) {
        Iterable<Book> books = bookService.findAllByTitle(title);
        return ResponseEntity.ok(books);
    }

    @PostMapping(value = "/uploadImage", consumes = {"multipart/form-data"})
    public String uploadImage(@ModelAttribute SampleRequest sr) {
        fileStorageService.uploadFile(sr.getImage());
        String message =  "You successfully uploaded " + sr.getImage().getOriginalFilename() + "!";
        return message;
    }

    @GetMapping("download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String id) {
        ArrayList response;
        FileStorage file = new FileStorage();
        Resource resource = null;
        Long contentLength = null;
        try {

            response = fileStorageService.downloadFile(id);
            file = (FileStorage) response.get(0);
            resource = (Resource) response.get(1);
            contentLength = resource.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+file.getFileName());
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return ResponseEntity.ok()
                .headers(header)
                .contentLength(contentLength)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @GetMapping("fts")
    public ResponseEntity<SearchResult> search(@RequestParam String term) {
        SearchResult searchResult = fts.myMethod(term);
        return ResponseEntity.ok(searchResult);
    }
}
