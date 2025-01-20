package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Comment;
import com.example.rootsquad.backend.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CommentService implements CommentServiceInterface{

    @Autowired
    CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> findByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }
}
