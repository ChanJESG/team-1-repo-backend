package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentServiceInterface {
    public Comment save(Comment comment);
    public List<Comment> findAll();
    public Comment update(Comment comment);
    public void delete(Long id);
    public Optional<Comment> findById(Long id);
    public List<Comment> findByPostId(Long id);
    public List<Comment> findByUserId(Long id);
}
