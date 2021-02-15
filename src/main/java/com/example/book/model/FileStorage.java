package com.example.book.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class FileStorage {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    @Field
    private String documentId;

    @Field
    private String fileName;

    @Field
    private String documentType;

    @Field
    private String uploadDir;
}