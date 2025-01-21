package com.example.rootsquad.backend.dto;

import com.example.rootsquad.backend.model.Post;

public class CommentDto {

    private String commentBody;

    private Post post;

    public CommentDto() {
    }

    public CommentDto(String commentBody) {
        this.commentBody = commentBody;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
