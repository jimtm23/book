package com.example.book.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CrudController<Model> {

    @GetMapping("/show/{id}")
    public ResponseEntity<Model> show(@PathVariable String id);

    @GetMapping("/list")
    public ResponseEntity<Iterable<Model>> list();

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Model t);

    @PutMapping("/edit/{id}")
    public ResponseEntity<Model> edit(@PathVariable String id, @RequestBody Model t);

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody String id);

}
