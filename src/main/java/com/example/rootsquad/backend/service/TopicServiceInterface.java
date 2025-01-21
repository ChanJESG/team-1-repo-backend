package com.example.rootsquad.backend.service;

import com.example.rootsquad.backend.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicServiceInterface {
    public Topic save(Topic topic);
    public List<Topic> findAll();
    public Topic update(Topic topic);
    public void delete(Long id);
    public Optional<Topic> findById(Long id);
}
