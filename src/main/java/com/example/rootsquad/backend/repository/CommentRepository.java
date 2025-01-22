package com.example.rootsquad.backend.repository;

import com.example.rootsquad.backend.model.Comment;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CommentRepository extends ListCrudRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByUserId(Long userId);
}
