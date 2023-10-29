package com.project.bloggingapp.entities;

import com.project.bloggingapp.config.AppConstants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = AppConstants.USER_TABLE_NAME)
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(name = AppConstants.USER_EMAIL_COLUMN, nullable = false, length = AppConstants.USER_EMAIL_COLUMN_LENGTH)
    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = AppConstants.USER_MAPPED, cascade = CascadeType.ALL) //child and parent together add and remove
    private Set<Post> blogPosts;

}
