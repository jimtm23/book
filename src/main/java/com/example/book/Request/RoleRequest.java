package com.example.book.Request;

import com.example.book.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    private String roleId;
}
