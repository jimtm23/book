package com.example.book.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.NotNull;

import java.time.ZonedDateTime;

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
    private String author;

    @Field
    private String description;

    @Field String image;

    @Field String publisher;

    @Field String date;
    /*@Field
    private  FileStorage image;*/

}
