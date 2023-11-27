package com.project.bloggingapp.controllers;


import com.project.bloggingapp.config.AppConstants;
import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.PostDto;
import com.project.bloggingapp.payloads.PostResponse;
import com.project.bloggingapp.service.FileService;
import com.project.bloggingapp.service.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("project.image")
    private String FilePath;

    @PostMapping("/userId/{userId}/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse> addPost(@RequestBody PostDto postdto, @PathVariable("userId") Integer uid, @PathVariable("categoryId") Integer cid){
        PostDto savedPostDto = this.postService.createPost(postdto, cid, uid);
        ApiResponse apiResponse = new ApiResponse(AppConstants.CREATED_STATUS_CODE, AppConstants.POST_SAVED_SUCCESS, true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/userId/{userId}/posts")
    public ResponseEntity<ApiResponse> getPostByUsers(@PathVariable("userId") Integer uid,
                                                      @RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                      @RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                      @RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                      @RequestParam(value="sortOrder", defaultValue = AppConstants.SORT_ORDER, required = false) String sortOrder){
        PostResponse savedPostDto = this.postService.getAllPostByUser(uid, pageNumber, pageSize, sortBy, sortOrder);
        ApiResponse apiResponse = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_GETBYUSER_SUCCESS, true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<ApiResponse> getPostByCategory(@PathVariable("categoryId") Integer cid,
                                                         @RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
                                                         @RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
                                                         @RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
                                                           @RequestParam(value="sortOrder", defaultValue = AppConstants.SORT_ORDER, required = false) String sortOrder
    ){
        PostResponse savedPostDto = this.postService.getAllPostByCategory(cid, pageNumber, pageSize,sortBy, sortOrder);
        ApiResponse apiResponse = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_GETBYCATEGORY_SUCCESS, true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse> getPostByPostID(@PathVariable("postId") Integer pid){
        PostDto savedPostDto = this.postService.getPostById(pid);
        ApiResponse apiResponse = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_GETBYPOSTID_SUCCESS, true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortOrder", defaultValue = AppConstants.SORT_ORDER, required = false) String sortOrder

    ){
        PostResponse savedPostDto = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortOrder);
        ApiResponse apiResponse = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_GETALL_SUCCESS, true, savedPostDto);
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer pid){
        this.postService.deletePost(pid);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_DELETED_SUCCESS, true, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer pid){
        PostDto savedPost = this.postService.updatePost(postDto, pid);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_UPDATED_SUCCESS, true, savedPost);
        return ResponseEntity.ok(response);
    }

    //search
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<ApiResponse> searchPostByTitle(@PathVariable("keywords") String keyword){
        List<PostDto> postDtos = this.postService.searchPosts(keyword);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, String.format(AppConstants.POST_GETBYKEYWORD_SUCCESS,keyword), true, postDtos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("image/upload/{postId}")
    public ResponseEntity<ApiResponse> uploadPostImage(@PathVariable("postId") Integer postId, @RequestParam("image")MultipartFile image) throws IOException {
        PostDto postDto = this.postService.getPostById(postId);
        String fileUpload = this.fileService.uploadImage(this.FilePath, image);
        postDto.setImageName(fileUpload);
        PostDto savedpostDto = this.postService.updatePost(postDto, postId);
        ApiResponse response = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.POST_UPDATED_SUCCESS, true, savedpostDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    )throws IOException{
        InputStream resource = this.fileService.getResource(this.FilePath, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
