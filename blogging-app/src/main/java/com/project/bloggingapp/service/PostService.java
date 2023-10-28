package com.project.bloggingapp.service;

import com.project.bloggingapp.entities.Post;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer cid, Integer uid);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String order);

    PostDto getPostById(Integer PostId);

    PostResponse getAllPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize,String sortBy, String order);

    PostResponse getAllPostByUser(Integer userid, Integer pageNumber, Integer pageSize,String sortBy, String order);

    List<PostDto> searchPosts(String keyword);
}
