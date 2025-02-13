package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Post;
import com.example.rootsquad.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PostServiceInterface {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
       return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findByTopicId(Long topicId) {
        return postRepository.findByTopicId(topicId);
    }

    @Override
    public List<Post> findByCategoryId(Long categoryId) {
        return postRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    public List<Post> findByTitleContainingOrDescriptionContaining(String searchTerm) {
        return postRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @Override
    public List<Post> findAllByDescUpdateTime() {
        return postRepository.findAllByDescUpdateTime();
    }


}
