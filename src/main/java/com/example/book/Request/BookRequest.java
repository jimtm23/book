package com.example.book.Request;

        import com.fasterxml.jackson.annotation.JsonAutoDetect;
        import com.fasterxml.jackson.annotation.JsonInclude;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.experimental.Accessors;
        import org.springframework.data.couchbase.core.mapping.Field;

        import javax.validation.constraints.NotBlank;
        import javax.validation.constraints.NotNull;
        import java.time.ZonedDateTime;

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

    @NotBlank(message = "Author is required!")
    @NotNull(message = "Cannot be null")
    private String author;

    @NotBlank(message = "Description is required!")
    @NotNull(message = "Cannot be null")
    private String description;

    @NotBlank(message = "Publisher is required!")
    @NotNull(message = "Cannot be null")
    private String publisher;

    @NotBlank(message = "Date is required!")
    @NotNull(message = "Cannot be null")
    private String date;

    private String image;
}
