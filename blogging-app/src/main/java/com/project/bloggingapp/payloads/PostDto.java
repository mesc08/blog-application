package com.project.bloggingapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String postId;

    private String title;

    private String content;

    private String imageName;

    private Date addeddate;

    private CategoryDto category;

    private UserDto user;

    private Set<CommentDto> comments;
}
