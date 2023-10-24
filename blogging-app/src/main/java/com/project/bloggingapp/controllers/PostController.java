package com.project.bloggingapp.controllers;


import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/userId/{userId}/category/{categoryId}")
    public ResponseEntity<ApiResponse> addPost(@RequestBody PostDto postdto, @PathVariable("userId") Integer uid, @PathVariable("categoryId") Integer cid){
        PostDto savedPostDto = this.postService.createPost(postdto, cid, uid);
        ApiResponse apiResponse = new ApiResponse(202, "saved post", true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

}
