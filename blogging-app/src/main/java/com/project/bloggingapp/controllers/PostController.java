package com.project.bloggingapp.controllers;


import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.payloads.PostResponse;
import com.project.bloggingapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/userId/{userId}/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse> addPost(@RequestBody PostDto postdto, @PathVariable("userId") Integer uid, @PathVariable("categoryId") Integer cid){
        PostDto savedPostDto = this.postService.createPost(postdto, cid, uid);
        ApiResponse apiResponse = new ApiResponse(202, "saved post", true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/userId/{userId}/posts")
    public ResponseEntity<ApiResponse> getPostByUsers(@PathVariable("userId") Integer uid,
                                                      @RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                      @RequestParam(value="pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                      @RequestParam(value="sortBy", defaultValue = "postId", required = false) String sortBy,
                                                      @RequestParam(value="sortOrder", defaultValue = "asc", required = false) String sortOrder){
        PostResponse savedPostDto = this.postService.getAllPostByUser(uid, pageNumber, pageSize, sortBy, sortOrder);
        ApiResponse apiResponse = new ApiResponse(200, "got post by users", true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse> getPostByCategory(@PathVariable("categoryId") Integer cid,
                                                         @RequestParam(value="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                         @RequestParam(value="pageSize", defaultValue = "10", required = false) Integer pageSize,
                                                         @RequestParam(value="sortBy", defaultValue = "postId", required = false) String sortBy,
                                                           @RequestParam(value="sortOrder", defaultValue = "asc", required = false) String sortOrder
    ){
        PostResponse savedPostDto = this.postService.getAllPostByCategory(cid, pageNumber, pageSize,sortBy, sortOrder);
        ApiResponse apiResponse = new ApiResponse(200, "got post by category", true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> getPostByPostID(@PathVariable("postId") Integer pid){
        PostDto savedPostDto = this.postService.getPostById(pid);
        ApiResponse apiResponse = new ApiResponse(200, "got post by category", true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value="sortOrder", defaultValue = "asc", required = false) String sortOrder

    ){
        PostResponse savedPostDto = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortOrder);
        ApiResponse apiResponse = new ApiResponse(200, "got post by category", true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer pid){
        this.postService.deletePost(pid);
        ApiResponse response = new ApiResponse(200, "deleted category ", true, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer pid){
        PostDto savedPost = this.postService.updatePost(postDto, pid);
        ApiResponse response = new ApiResponse(200, "deleted category ", true, savedPost);
        return ResponseEntity.ok(response);
    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<ApiResponse> searchPostByTitle(@PathVariable("keywords") String keyword){
        List<PostDto> postDtos = this.postService.searchPosts(keyword);
        ApiResponse response = new ApiResponse(200, String.format("got post by keyword %s",keyword), true, postDtos);
        return ResponseEntity.ok(response);
    }
}
