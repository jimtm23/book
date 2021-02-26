package com.example.book.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class SampleRequest {
    @NotBlank()
    @NotNull(message = "Cannot be null")
    private String title;

    @NotBlank()
    @NotNull(message = "Cannot be null")
    private String author;

    @NotBlank()
    @NotNull(message = "Cannot be null")
    private String description;
    MultipartFile image;
}
