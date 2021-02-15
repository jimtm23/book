package com.example.book.repository;

import com.example.book.model.FileStorage;
import org.springframework.data.repository.CrudRepository;

public interface FileStorageRepository extends CrudRepository<FileStorage, String> {
}
