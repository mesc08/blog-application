package com.project.bloggingapp.repositories;

import com.project.bloggingapp.utils.AppConstants;
import com.project.bloggingapp.entities.Category;
import com.project.bloggingapp.entities.Post;
import com.project.bloggingapp.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findAllByUser(User user, Pageable page);

    Page<Post> findAllByCategory(Category category, Pageable page);


    //if content findByContentContaining
    @Query(AppConstants.SEARCH_KEYWORD_QUERY)
    List<Post> searchTitleKeyword(@Param("key") String title);
}
