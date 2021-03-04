package com.example.book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.NotNull;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Author {

    @Id
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    @NotNull
    @Field
    private String fullName;
}