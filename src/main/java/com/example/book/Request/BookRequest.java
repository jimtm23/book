package com.example.book.Request;

import com.example.book.model.Author;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotBlank(message = "Title is required!")
    @NotNull(message = "Cannot be null")
    private String title;

    @NotNull(message = "Cannot be null")
    private List<AuthorRequest> authors;

    @NotBlank(message = "Description is required!")
    @NotNull(message = "Cannot be null")
    private String description;

    @NotBlank(message = "Publisher is required!")
    @NotNull(message = "Cannot be null")
    private String publisher;

    @NotBlank(message = "Date is required!")
    @NotNull(message = "Cannot be null")
    private String date;

    @NotBlank(message = "ISBN is required!")
    @NotNull(message = "Cannot be null")
    private String ISBN;

    private String image;


}
