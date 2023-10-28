package com.project.bloggingapp.service;

import com.project.bloggingapp.entities.Post;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer cid, Integer uid);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    PostDto getPostById(Integer PostId);

    List<PostDto> getAllPostByCategory(Integer categoryId);

    List<PostDto> getAllPostByUser(Integer userid);

    List<PostDto> searchPosts(String keyword);
}
