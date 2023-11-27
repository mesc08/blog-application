package com.project.bloggingapp.entities;


import com.project.bloggingapp.utils.AppConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = AppConstants.COMMENT_TABLE)
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;


    private String content;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;
}
