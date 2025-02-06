package com.example.rootsquad.backend.repository;

import com.example.rootsquad.backend.model.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PostRepository extends ListCrudRepository<Post, Long> {
    List<Post> findByCategoryId(Long id);

    List<Post> findByTopicId(Long id);

    List<Post> findByUserId(Long id);

    List<Post> findByTitleContainingOrDescriptionContaining(String searchTerm, String searchTerm2);

    List<Post> findByTitleContainingOrDescriptionContainingAndTopicIdIs(String searchTerm, String searchTerm2, Long topicId);

    List<Post> findByTitleContainingOrDescriptionContainingAndCategoryIdIs(String searchTerm, String searchTerm2, Long categoryId);
}
