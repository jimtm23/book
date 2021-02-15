package com.example.book.Request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequest {

    @NotBlank()
    @NotNull
    private String title;

    @NotBlank()
    @NotNull
    private String author;

    @NotBlank()
    @NotNull
    private String description;
}
