package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostServiceInterface {
    public Post save (Post post);
    public List<Post> findAll();
    public Post update(Post post);
    public void delete(Long id);
    public Optional<Post> findById(Long id);
    public List<Post> findByTopicId(Long topicId);
    public List<Post> findByCategoryId(Long categoryId);
    public List<Post> findByUserId(Long userId);

}
