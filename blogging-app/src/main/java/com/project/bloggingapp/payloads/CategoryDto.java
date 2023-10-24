package com.project.bloggingapp.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer catid;

    @NotEmpty(message = "category title cannot be empty")
    @Size(min=3, message = "category size should be greater than 3")
    private String catTitle;

    @NotEmpty(message = "category description cannot be empty")
    @Size(min=8, message = "category description cannot be empty")
    private String description;
}
