package com.project.bloggingapp.controllers;

import com.project.bloggingapp.utils.AppConstants;
import com.project.bloggingapp.payloads.ApiResponse;
import com.project.bloggingapp.payloads.CommentDto;
import com.project.bloggingapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("post/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> addedComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId){
        CommentDto commentDto1 = this.commentService.createComment(commentDto, postId, userId);
        ApiResponse apiResponse = new ApiResponse(AppConstants.CREATED_STATUS_CODE, AppConstants.COMMENT_ADDED_MSG, true, commentDto1);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<ApiResponse> deletedComment(@PathVariable("commentId") Integer commentId){
        this.commentService.deleteComment(commentId);
        ApiResponse apiResponse = new ApiResponse(AppConstants.OK_STATUS_CODE, AppConstants.COMMENT_DELETED_MSG, true, null);
        return ResponseEntity.ok(apiResponse);
    }
}
