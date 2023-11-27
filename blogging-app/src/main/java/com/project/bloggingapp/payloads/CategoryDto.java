package com.project.bloggingapp.payloads;

import com.project.bloggingapp.utils.AppConstants;
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

    @NotEmpty(message = AppConstants.CATEGORY_TITLE_NOT_EMPTY)
    @Size(min=AppConstants.CATEGORY_TITLE_SIZE, message = AppConstants.CATEGORY_TITLE_SIZE_MSG)
    private String categoryTitle;

    @NotEmpty(message = AppConstants.CATEGORY_DESCRIPTION_NOT_EMPTY)
    @Size(min=AppConstants.CATEGORY_DESCRIPTION_SIZE, message = AppConstants.CATEGORY_DESCRIPTION_SIZE_MSG)
    private String description;
}
