package com.example.rootsquad.backend.dto;

import com.example.rootsquad.backend.model.Post;

public class CommentDto {

    private String commentBody;
    private Long postId;
    private Long userId;

    public CommentDto() {
    }

    public CommentDto(String commentBody, Long userId) {
        this.commentBody = commentBody;
        this.userId = userId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
