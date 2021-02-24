package com.example.book.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class PermissionRequest {

    @NotNull
    private String name;
}
