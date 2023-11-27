package com.project.bloggingapp.implementation;

import com.project.bloggingapp.utils.AppConstants;
import com.project.bloggingapp.entities.Category;
import com.project.bloggingapp.entities.Post;
import com.project.bloggingapp.entities.User;
import com.project.bloggingapp.exceptions.ResourceNotFoundException;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.payloads.PostResponse;
import com.project.bloggingapp.repositories.CategoryRepository;
import com.project.bloggingapp.repositories.PostRepository;
import com.project.bloggingapp.repositories.UserRepository;
import com.project.bloggingapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equals(AppConstants.SORT_ORDER) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = this.postRepository.findAll(page);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postdtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer PostId) {
        Post post = this.postRepository.findById(PostId).orElseThrow(() -> new ResourceNotFoundException("post ", " post id ", PostId));
        PostDto foundPostDto = this.modelMapper.map(post, PostDto.class);
        return foundPostDto;
    }

    @Override
    public PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize,String sortBy, String order) {
        Sort sort = order.equals(AppConstants.SORT_ORDER) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("post ", " cid ", categoryId));
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> Pageposts = this.postRepository.findAllByCategory(category, page);
        List<Post> posts = Pageposts.getContent();
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postdtos);
        return postResponse;
    }

    @Override
    public PostResponse getAllPostByUser(Integer userid, Integer pageNumber, Integer pageSize,String sortBy, String order) {
        Sort sort = order.equals(AppConstants.SORT_ORDER) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        User user = this.userRepository.findById(userid).orElseThrow(() -> new ResourceNotFoundException("post ", " uid ", userid));
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> Pageposts = this.postRepository.findAllByUser(user, page);
        List<Post> posts = Pageposts.getContent();
        List<PostDto> postdtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postdtos);
        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepository.searchTitleKeyword("%" + keyword + "%");
        List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
