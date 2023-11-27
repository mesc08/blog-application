package com.project.bloggingapp.entities;

import com.project.bloggingapp.utils.AppConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = AppConstants.CATEGORY_TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catId;

    @Column(name = AppConstants.CATEGORY_TITLE, nullable = false)
    private String categoryTitle;

    @Column(name = AppConstants.CATEGORY_DESCRIPTION)
    private String description;

    @OneToMany(mappedBy = AppConstants.CATEGORY_MAPPED, cascade = CascadeType.ALL) //child and parent together add and remove
    private Set<Post> blogPosts;
}
