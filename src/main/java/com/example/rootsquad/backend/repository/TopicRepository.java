package com.example.rootsquad.backend.repository;

import com.example.rootsquad.backend.model.Topic;
import org.springframework.data.repository.ListCrudRepository;

public interface TopicRepository extends ListCrudRepository<Topic, Long> {
}
