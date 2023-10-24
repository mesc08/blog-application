package com.project.bloggingapp.implementation;

import com.project.bloggingapp.entities.Category;
import com.project.bloggingapp.entities.Post;
import com.project.bloggingapp.entities.User;
import com.project.bloggingapp.exceptions.ResourceNotFoundException;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.repositories.CategoryRepository;
import com.project.bloggingapp.repositories.PostRepository;
import com.project.bloggingapp.repositories.UserRepository;
import com.project.bloggingapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, Integer cid, Integer uid) {
        User user = this.userRepository.findById(uid).orElseThrow(() -> new ResourceNotFoundException("post ", " user id ", uid));
        Category category = this.categoryRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("post ", " category id ", cid));
        Post newPost = this.modelMapper.map(postDto, Post.class);
        newPost.setImageName("default.png");
        newPost.setAddeddate(new Date());
        newPost.setUser(user);
        newPost.setCategory(category);
        Post savedPost = this.postRepository.save(newPost);
        PostDto post = this.modelMapper.map(savedPost, PostDto.class);
        return post;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post ", " post id ", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        post.setAddeddate(new Date());
        Post updatedPost = this.postRepository.save(post);
        PostDto newpostdto = this.modelMapper.map(updatedPost, PostDto.class);
        return newpostdto;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post ", " post id ", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = this.postRepository.findAll();
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public PostDto getPostById(Integer PostId) {
        Post post = this.postRepository.findById(PostId).orElseThrow(() -> new ResourceNotFoundException("post ", " post id ", PostId));
        PostDto foundPostDto = this.modelMapper.map(post, PostDto.class);
        return foundPostDto;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("post ", " cid ", categoryId));
        List<Post> posts = this.postRepository.findAllByCategory(category);
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userid) {
        User user = this.userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("post ", " uid ", userid));
        List<Post> posts = this.postRepository.findAllByUser(user);
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }
}
