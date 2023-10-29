package com.project.bloggingapp.implementation;

import com.project.bloggingapp.entities.Comment;
import com.project.bloggingapp.entities.Post;
import com.project.bloggingapp.entities.User;
import com.project.bloggingapp.exceptions.ResourceNotFoundException;
import com.project.bloggingapp.payloads.CommentDto;
import com.project.bloggingapp.repositories.CommentRepository;
import com.project.bloggingapp.repositories.PostRepository;
import com.project.bloggingapp.repositories.UserRepository;
import com.project.bloggingapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("comment ", " user ", userId));
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("comment ", " post ", postId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedcomment = this.commentRepository.save(comment);
        CommentDto newCommentDto = this.modelMapper.map(savedcomment, CommentDto.class);
        return newCommentDto;
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment ", " comment ", commentId));
        this.commentRepository.delete(comment);
    }
}
