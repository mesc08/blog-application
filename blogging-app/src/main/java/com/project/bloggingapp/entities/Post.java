package com.project.bloggingapp.entities;

import com.project.bloggingapp.config.AppConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = AppConstants.POST_TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = AppConstants.POST_TITLE_COLUMN_NAME, length = AppConstants.POST_TITLE_SIZE, nullable = false)
    private String title;

    @Column(length = AppConstants.POST_CONTENT_COLUMN_SIZE)
    private String content;

    private String imageName;

    private Date addeddate;

    @ManyToOne
    @JoinColumn(name = AppConstants.POST_CATEGORY_COLUMN_JOIN)
    private Category category;

    @ManyToOne
    @JoinColumn(name = AppConstants.POST_USER_COLUMN_JOIN)
    private User user;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private Set<Comment> comments;
}
