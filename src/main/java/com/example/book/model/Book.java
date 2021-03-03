package com.example.book.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.NotNull;

import java.util.List;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Book {


    @Id @GeneratedValue(strategy = UNIQUE)
    private String id;

    @NotNull
    @Field
    private String title;

    @NotNull
    @Field
    private List<Author> authors;

    @Field
    private String description;

    @Field String image;

    @Field String publisher;

    @Field String dateAdded;

    @Field String ISBN;

}
