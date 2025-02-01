package com.example.rootsquad.backend.controller;

import com.example.rootsquad.backend.dto.CommentDto;
import com.example.rootsquad.backend.exception.ResourceNotFoundException;
import com.example.rootsquad.backend.model.Comment;
import com.example.rootsquad.backend.model.Post;
import com.example.rootsquad.backend.model.User;
import com.example.rootsquad.backend.service.CommentServiceInterface;
import com.example.rootsquad.backend.service.PostServiceInterface;
import com.example.rootsquad.backend.service.UserServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restricted/api/comment")
public class RestrictedCommentController {

    @Autowired
    PostServiceInterface postService;
    @Autowired
    CommentServiceInterface commentService;
    @Autowired
    UserServiceInterface userService;

    // create new comment via postId
    @PostMapping("/post={postId}")
    public ResponseEntity<Comment> addComment(@PathVariable("postId") Long postId, @RequestParam("commentData") String commentData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDto commentDto = objectMapper.readValue(commentData, CommentDto.class);
        Post post = postService.findById(postId).orElseThrow(()-> new ResourceNotFoundException());
        User user = userService.findById(commentDto.getUserId()).orElseThrow(()-> new ResourceNotFoundException());

        Comment savedComment = new Comment();
        savedComment.setCommentBody(commentDto.getCommentBody());
        savedComment.setPost(post);
        savedComment.setUser(user);

        return new ResponseEntity<>(commentService.save(savedComment), HttpStatus.CREATED);
    }

    // update comment
    @PutMapping("/comment={commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable("commentId") Long commentId, @RequestParam("commentData") String commentData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDto commentDto = objectMapper.readValue(commentData, CommentDto.class);

        Comment updatedComment = commentService.findById(commentId).map(foundComment -> {
            foundComment.setCommentBody(commentDto.getCommentBody());

            return commentService.update(foundComment);
        }).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // get comments list
    @GetMapping
    public ResponseEntity<Object> getComments() {
        List<Comment> commentList = commentService.findAll();

        if(commentList.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    // get comment by id
    @GetMapping("/comment={commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Long commentId) {
        Comment comment = commentService.findById(commentId).orElseThrow(()-> new ResourceNotFoundException());

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // get comments list by postId
    @GetMapping("/post={postId}")
    public ResponseEntity<Object> getCommentsByPostId(@PathVariable("postId") Long postId) {
        List<Comment> commentList = commentService.findByPostId(postId);

        if(commentList.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    // get comments list by userId
    @GetMapping("/user={userId}")
    public ResponseEntity<Object> getCommentsByUserId(@PathVariable("userId") Long userId) {
        List<Comment> commentList = commentService.findByUserId(userId);

        if (commentList.isEmpty())
            throw new ResourceNotFoundException();

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    // delete comment
    @DeleteMapping("/comment={commentId}")
    public ResponseEntity<Comment> deleteCommentById(@PathVariable("commentId") Long commentId) {
        Comment deletedComment = commentService.findById(commentId).orElseThrow(()-> new ResourceNotFoundException());

        commentService.delete(commentId);
        return new ResponseEntity<>(deletedComment,HttpStatus.OK);
    }
}
